package com.voxwalker.lbr.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.entity.Word;
import com.voxwalker.lbr.model.WordSet;
import com.voxwalker.lbr.repository.WordRepository;

@Service
public class WordService {
	private static final int PAGE_SIZE = 10;

	@Autowired
	private WordRepository wordRepository;

	public Set<String> findByUserAndLangWordKnown(Long id, String lang) {
		return new HashSet<String>(wordRepository.findByUserAndLangWordKnown(id, lang));

	}

	public Set<String> findByUserAndLangWordUnknown(Long id, String lang) {
		return new HashSet<String>(wordRepository.findByUserAndLangWordUnknown(id, lang));
	}

	public void save(User user, String lang, WordSet wordSet) {
		saveWordSet(user, lang, wordSet.getUnknown(), 1);
		saveWordSet(user, lang, wordSet.getKnown(), 0);
 
		
	}
	
	private void saveWordSet(User user, String lang, Set<String> words, int state){
		 for (String txt : words){
			  Word word = wordRepository.findByUserAndTxt(user, txt);
			  
			 if ( word != null){  // 
				 word.setState(state);
				 word.setReviewedDate(new Date());
				 wordRepository.save(word);
			 }else{   //
				 Word newWord = new Word();
				 newWord.setLang(lang);
				 newWord.setTxt(txt);
				 newWord.setUser(user);
				 newWord.setState(state);
				 newWord.setAddedDate(new Date());
				 newWord.setReviewedDate(new Date());
				 wordRepository.save(newWord);				 
			 }
		 }
	}

	public Page<Word> getPage( int page_number) {
		PageRequest pageRequest = new PageRequest(page_number -1 , PAGE_SIZE, Sort.Direction.DESC, "reviewedDate");
		Page<Word> p;
		 
		//return wordRepository.findByUserAndLangAndState(user, lang, 1, pageRequest);
		return wordRepository.findAll(pageRequest);
	}



}
