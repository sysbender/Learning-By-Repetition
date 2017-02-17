package com.voxwalker.lbr.controller;

import java.io.File;
import java.net.URL;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.Item;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.entity.Upload;
import com.voxwalker.lbr.service.ItemService;
import com.voxwalker.lbr.service.LessonService;
import com.voxwalker.lbr.service.UploadService;
import com.voxwalker.lbr.service.UserService;

@Controller
public class LessonController {

	@Autowired
	private LessonService lessonService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private UserService userService;

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
		Course course = lessonService.findCourseByLesson(lesson);
		System.out.println(lesson_id + " = id;====================== name = "
				+ lesson.getName());
		List<Upload> uploads = uploadService.findByLesson(lesson);
		List<Item> items = itemService.findByLesson(lesson);
		System.out.println("====================item size =" + items.size() +"upload size:" + uploads.size());
		model.addAttribute("course",course);
		model.addAttribute("lesson",lesson);
		model.addAttribute("items", items);
		model.addAttribute("uploads", uploads);
		return "import-lesson";
	}

	@RequestMapping(value = "/import/lesson/{lesson_id}", method = RequestMethod.POST, params = "item")
	public String doAddItem(@PathVariable long lesson_id,
			@ModelAttribute("item") Item item) {
		System.out.println("=========new item =======");
		Lesson lesson = lessonService.findOne(lesson_id);
		item.setLesson(lesson);
		itemService.save(item);

		return "redirect:/import/lesson/" + lesson_id + ".html";
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


	@RequestMapping(value = "/import/lesson/{lesson_id}", method = RequestMethod.POST, params = "upload")
	public String doUpload(@PathVariable long lesson_id,
			@ModelAttribute("upload") Upload upload,
			@RequestParam("multipartFile") MultipartFile multipartFile,
			HttpServletRequest request, Principal principal) {
		System.out.println("=========new upload =======");

		if (!multipartFile.isEmpty()) {
			try {
				Lesson lesson = lessonService.findOne(lesson_id);
				Long course_id = lesson.getCourse().getId();
				String username = principal.getName();
				Long user_id = userService.findByName(username).getId();

				// relativePath = /resource/upload/user_id/course_id/lesson_id
				String relativePath = File.separator + "upload"
						+ File.separator + user_id + File.separator + course_id
						+ File.separator + lesson_id;
				File absolutePath = new File(request.getServletContext()
						.getRealPath("/") + relativePath);
				if (!absolutePath.exists()) {
					absolutePath.mkdirs();

				}
				System.out.println("================upload to dir: "
						+ absolutePath.getAbsolutePath());

				// move file to new name: random uuid + extension
				String newFilename =multipartFile.getOriginalFilename().replaceAll("[^a-zA-Z0-9.]", "-");

				File serverFile = new File(absolutePath.getAbsolutePath()
						+ File.separator + newFilename);
				multipartFile.transferTo(serverFile);

				// save info to upload table
				System.out.println(" ============@@@@@@@@@@  canonical:"
						+ serverFile.getCanonicalPath());
				System.out.println(" name:" + serverFile.getName());
				System.out.println(" AbsolutePath:"
						+ serverFile.getAbsolutePath());
				upload.setSize(multipartFile.getSize());
				upload.setLesson(lesson);
				upload.setFile(serverFile.getCanonicalPath());
				
				System.out.println("relative path = "+ relativePath);
				String temp = relativePath.replace("\\", "/");
				System.out.println( "after replace = " + temp);
				
				// from stackoverflow
				URL requestURL = new URL(request.getRequestURL().toString());
			    String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
			    String baseUrl = requestURL.getProtocol() + "://" + requestURL.getHost() + port;
			    
				upload.setUrl( baseUrl + relativePath.replace("\\", "/")+"/"+newFilename);
				
				upload.setName(multipartFile.getOriginalFilename());
				uploadService.save(upload);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "redirect:/import/lesson/" + lesson_id + ".html";
	}
}
