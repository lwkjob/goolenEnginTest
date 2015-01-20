package com.lwk.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jqTest")
public class JqGridController {
	
	@RequestMapping("/test")
	public String jq(){
		return "madan";
	}
}
