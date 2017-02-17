package com.voxwalker.lbr.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.entity.Word;

public class WordRepositoryTest extends BaseSpringTest {
 
	@Autowired
	UserRepository userRepository;
	@Autowired
	WordRepository wordRepository;

	@Test
	public void testFindByUserAndLangAndState() {
//		User user = new User();
//		user.setName("admin");
		User user = userRepository.findByName("admin");
		
		
		Word word = new Word();
		word.setUser(user);
		word.setTxt("connu");
		word.setLang("fr");
		word.setState(0);
		wordRepository.save(word);
		
		Word word1 = new Word();
		word1.setUser(user);
		word1.setTxt("unconnu");
		word1.setLang("fr");
		word1.setState(1);
		wordRepository.save(word1);
		

		List<Word> words = wordRepository.findByUserAndLangAndState(user, "fr", 0);
		for(Word w : words){
			System.out.println("known :" +  w.getTxt());
		}
		
		
		words = wordRepository.findByUserAndLangAndState(user, "fr", 1);
		for(Word w : words){
			System.out.println("unknown :" + w.getTxt());
		}
	}

}
