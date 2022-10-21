package com.son.hrm.common.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ResponseHelper {
	
	public static ResponseEntity<Object> getResponse(Object obj, HttpStatus status) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("timeStamp", LocalDateTime.now());
		map.put("hasError", false);
		map.put("content", obj);
		map.put("status", status.value());
		
		return new ResponseEntity<Object>(map, status);
	}
	
	public static ResponseEntity<Object> getErrorResponse(BindingResult result, HttpStatus status) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("timeStamp", LocalDateTime.now());
		map.put("hasError", true);
		map.put("errors", ErrorHelper.getError(result));
		map.put("status", status.value());
		
		return new ResponseEntity<Object>(map, status);
	} 
	
	public static ResponseEntity<Object> getErrorResponse(String message, HttpStatus status) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("timeStamp", LocalDateTime.now());
		map.put("hasError", true);
		map.put("errors", message);
		map.put("status", status.value());
		
		return new ResponseEntity<Object>(map, status);
	} 
}
