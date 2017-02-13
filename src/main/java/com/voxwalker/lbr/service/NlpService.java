package com.voxwalker.lbr.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

@Service
public class NlpService {

	public static List<String> tokenizeList(String lang, String txt) {

		// pattern for split
		String pattern = "[^a-zA-Z]";
		if ("fr".equalsIgnoreCase(lang)) {
			pattern = "[^a-zA-Zùûüÿàâæçéèêëïîôœ]";
		}
		// guava split the text
		Iterable<String> iterable = Splitter.onPattern(pattern).trimResults()
				.omitEmptyStrings().split(txt);
		// convert the string list to lower case
		List<String> words = Lists.newArrayList(iterable);
		ListIterator<String> wordsIterator = words.listIterator();

		String word = "";
		while (wordsIterator.hasNext()) {
			word = wordsIterator.next();
			// remove alphabets
			if (word.length() < 2) {
				wordsIterator.remove();
			} else {
				wordsIterator.set(word.toLowerCase());
			}
		}
		return words;
	}

	public static Set<String> tokenizeSet(String lang, String txt) {
		return new HashSet<String>(tokenizeList(lang, txt));
	}
}
