package com.voxwalker.lbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voxwalker.lbr.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
