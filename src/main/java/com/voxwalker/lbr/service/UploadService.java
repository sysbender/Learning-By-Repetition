package com.voxwalker.lbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.entity.Upload;
import com.voxwalker.lbr.repository.UploadRepository;

@Service
public class UploadService {
	@Autowired
	private UploadRepository uploadRepository;

	public List<Upload> findByLesson(Lesson lesson) {
		 
		return uploadRepository.findByLesson(lesson);
	}

	public Upload findOne(long id) {
		 
		return uploadRepository.findOne(id);
	}

	public void delete(Upload upload) {
		uploadRepository.delete(upload);
		
	}

	public void save(Upload upload) {
		uploadRepository.save(upload);
		
	}
}
