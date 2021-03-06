package com.voxwalker.lbr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voxwalker.lbr.entity.Item;
import com.voxwalker.lbr.entity.Lesson;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByLesson(Lesson lesson);
	

}
