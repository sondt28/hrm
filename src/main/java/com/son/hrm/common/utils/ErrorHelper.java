package com.son.hrm.common.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class ErrorHelper {

	public static List<String> getError(BindingResult result) {
		
		return result.getAllErrors()
				.stream()
				.map(t -> t.getDefaultMessage())
				.collect(Collectors.toList());
	}
}
