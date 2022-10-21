package com.son.hrm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class Welcome {

	@GetMapping
	public String welcome() {
		return "Welcome to HRM application !";
	}
}
