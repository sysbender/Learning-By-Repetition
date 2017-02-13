package com.voxwalker.lbr.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class NlpServiceTest {
	String txt, frtxt;

	@Before
	public void setUp() throws Exception {
		txt = "The Three Little Pigs is a fable/fairy tale featuring anthropomorphic pigs who build three houses of different materials. A big bad wolf blows down the first two pigs' houses, made of straw and sticks respectively, but is unable to destroy the third pig's house, made of bricks. Printed versions date back to the 1840s, but the story itself is thought to be much older. The phrases used in the story, and the various morals drawn from it, have become embedded in Western culture.";
		frtxt ="- « Ah, ah, ah ! tu n’as toujours pas fini ta maison, la mienne est terminée depuis un moment déjà ! Maintenant, je vais pourvoir m’amuser » cria le premier. - « Ah ah oui moi aussi, tu devrais lâcher tes briques et venir jouer avec nous » gloussa le deuxième. - « Rira bien qui rira le dernier, le travail est toujours récompensé » répondit le troisième petit cochon qui se remit aussitôt au travail.";
	}

	@Test
	public void testTokenizeList() {
		 
		
		 
		List<String> words = NlpService.tokenizeList("en", txt);
		System.out.println(" words list:" + words.size());
		System.out.println(Arrays.toString(words.toArray() ));
		
		 
		List<String> frwords = NlpService.tokenizeList("fr", frtxt);
		System.out.println(" frwords list:" + frwords.size());
		System.out.println(Arrays.toString(frwords.toArray() ));
	}

	@Test
	public void testTokenizeSet() {
		
		Set<String> words = NlpService.tokenizeSet("en", txt);
		System.out.println(" words set :" + words.size());
		System.out.println(Arrays.toString(words.toArray() ));
		
		
		Set<String> frwords = NlpService.tokenizeSet("fr", frtxt);
		System.out.println(" fr words set :" + frwords.size());
		System.out.println(Arrays.toString(frwords.toArray() ));
	}

}
