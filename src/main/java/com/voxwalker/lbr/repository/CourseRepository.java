package com.voxwalker.lbr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.User;

public interface CourseRepository extends JpaRepository<Course, Long> {

		List<Course> findByUser(User user);

		int countByUserAndLang(User user, String lang);

		List<Course> findByUserAndLang(User user, String lang);

}
