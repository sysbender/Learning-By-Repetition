package com.voxwalker.lbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voxwalker.lbr.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
