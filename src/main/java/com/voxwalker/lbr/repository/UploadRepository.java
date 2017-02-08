package com.voxwalker.lbr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.entity.Upload;

public interface UploadRepository extends JpaRepository<Upload, Long> {

	List<Upload> findByLesson(Lesson lesson);
	

}
