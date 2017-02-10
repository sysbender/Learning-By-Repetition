package com.voxwalker.lbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Item;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findByLesson(Lesson lesson) {
		 
		return itemRepository.findByLesson(lesson);
	}

	public Item findOne(long id) {
		return itemRepository.findOne(id);
		
	}

	public void delete(Item item) {
		itemRepository.delete(item);
		
	}

	public void save(Item item) {
		itemRepository.save(item);
		
	}
	

}
