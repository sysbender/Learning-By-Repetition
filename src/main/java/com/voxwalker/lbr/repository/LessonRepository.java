package com.voxwalker.lbr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

	List<Lesson> findByCourse( Course course);

}
