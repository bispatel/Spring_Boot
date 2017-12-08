package com.spring.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	// inject via application.properties. Test is the default value
	@Value("${app.welcome.message:test}")
	private String MESSAGE = "";

	@Value("${app.welcome.title}")
	private String TITLE = "";

	@RequestMapping("/web")
	public String welcome(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "welcome";
	}

	 

}