package com.voxwalker.lbr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voxwalker.lbr.entity.Item;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.entity.Upload;
import com.voxwalker.lbr.service.ItemService;
import com.voxwalker.lbr.service.LessonService;
import com.voxwalker.lbr.service.UploadService;

@Controller
public class LessonController {

	@Autowired
	private LessonService lessonService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UploadService uploadService;

	// init lesson item for itemForm
	@ModelAttribute("item")
	private Item constructItem(HttpServletRequest request) {
		return new Item();
	}

	// init lesson upload for uploadForm
	@ModelAttribute("upload")
	private Upload constructUpload(HttpServletRequest request) {
		return new Upload();
	}

	@RequestMapping("/import/lesson/{lesson_id}")
	public String lesson(@PathVariable Long lesson_id, Model model) {

		Lesson lesson = lessonService.findOne(lesson_id);
		System.out.println(lesson_id + " = id;====================== name = "
				+ lesson.getName());
		List<Item> items = itemService.findByLesson(lesson);
		System.out.println("====================item size =" + items.size());
		model.addAttribute("items", itemService.findByLesson(lesson));
		model.addAttribute("uploads", uploadService.findByLesson(lesson));
		return "import-lesson";
	}

	@RequestMapping(value="/import/lesson/{lesson_id}" , method=RequestMethod.POST, params="item")
	public String doAddItem( @PathVariable long lesson_id, @ModelAttribute("item") Item item ){
		System.out.println( "=========new item =======");
		Lesson lesson = lessonService.findOne(lesson_id);
		item.setLesson(lesson);
		itemService.save(item);
		
		return "redirect:/import/lesson/" + lesson_id +".html";
	}
	
	@RequestMapping(value="/import/lesson/{lesson_id}" , method=RequestMethod.POST, params="upload")
	public String doAddItem( @PathVariable long lesson_id, @ModelAttribute("upload") Upload upload ){
		System.out.println( "=========new upload =======");
		return "redirect:/import/lesson/" + lesson_id +".html";
	}
	
	@RequestMapping("/import/item/remove/{item_id}")
	public String removeItem(@PathVariable long item_id) {
		System.out.println("==========remove Item============");

		// TODO : security
		Item item = itemService.findOne(item_id);
		long lesson_id = item.getLesson().getId();
		itemService.delete(item);
		return "redirect:/import/lesson/" + lesson_id + ".html";

	}

	@RequestMapping("/import/upload/remove/{upload_id}")
	public String removeUpload(@PathVariable long upload_id) {
		System.out.println("==========remove upload============");
		// TODO : security
		Upload upload = uploadService.findOne(upload_id);
		long lesson_id = upload.getLesson().getId();
		uploadService.delete(upload);
		return "redirect:/import/lesson/" + lesson_id + ".html";
	}

}
