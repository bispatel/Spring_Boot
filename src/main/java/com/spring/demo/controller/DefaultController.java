package com.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
	@GetMapping("/")
	public String index() {
		return "/home";
	}

	@GetMapping("/home")
	public String home() {
		return "/home";
	}

	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}

	@GetMapping("/user")
	public String user() {
		return "/user";
	}

	@GetMapping("/about")
	public String about() {
		return "/about";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	@GetMapping("/search")
	public String searchUser() {
		return "searchUser";
	}
	@GetMapping("/fileUpload")
	public String fileUpload() {
		return "/upload/upload";
	}
	
	@GetMapping("/fileUploadSingle")
	public String fileUploadSingle() {
		return "/upload/singleFileUpload";
	}
	@GetMapping("/fileUploadMultiple")
	public String multiFileUpload() {
		return "/upload/fileUpload";
	}
	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}
	
}
