package com.wesley.bean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewFileController {

	
	@GetMapping("/NewFile")
	public String getNewFile() {
		return "NewFile";
	}
}
