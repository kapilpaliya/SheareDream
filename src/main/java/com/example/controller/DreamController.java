package com.example.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Dream;
import com.example.service.DreamService;

@Controller
public class DreamController {
	@Autowired
	private DreamService dreamService;
	
	@GetMapping("/")
	public String getHome(Model m) {
		m.addAttribute("dreamList", dreamService.getAll());
		return "index";
	}
	
	@PostMapping("/message")
	public String getMessage(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("image") MultipartFile multipartFile, @RequestParam("text") String text) {
		Date datex = new Date();
		int day = datex.getDate();
		int month = datex.getMonth() +1;
		int year = datex.getYear() +1900;
		Dream dream = new Dream();
		dream.setEmail(email);
		dream.setName(name);
		dream.setText(text);
		dream.setDate(day+"-"+month+"-"+year);
		dream.setImage(multipartFile.getOriginalFilename());
		try {
			File pathFile = new ClassPathResource("/static" + File.separator + "DBImage").getFile();
			Path path = Paths.get(pathFile.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());
			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Image Save Success!");
			System.out.println(path);
			dreamService.saveDream(dream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	
	
}
