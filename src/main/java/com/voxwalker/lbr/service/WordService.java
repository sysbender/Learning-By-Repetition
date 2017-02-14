package com.voxwalker.lbr.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.entity.Word;
import com.voxwalker.lbr.model.WordSet;
import com.voxwalker.lbr.repository.WordRepository;

@Service
public class WordService {

	@Autowired
	private WordRepository wordRepository;

	public Set<String> findByUserWordKnown(Long id) {
		return new HashSet<String>(wordRepository.findByUserWordKnown(id));

	}

	public Set<String> findByUserWordUnknown(Long id) {
		return new HashSet<String>(wordRepository.findByUserWordUnknown(id));

	}

	public void save(User user, WordSet wordSet) {
		saveWordSet(user, wordSet.getUnknown(), 1);
		saveWordSet(user, wordSet.getKnown(), 0);
 
		
	}
	
	private void saveWordSet(User user, Set<String> words, int state){
		 for (String txt : words){
			  Word word = wordRepository.findByUserAndTxt(user, txt);
			 if ( word != null){
				 word.setState(state);
				 wordRepository.save(word);
			 }else{
				 Word newWord = new Word();
				 newWord.setTxt(txt);
				 newWord.setUser(user);
				 newWord.setState(state);
				 wordRepository.save(newWord);				 
			 }
		 }
	}



}
