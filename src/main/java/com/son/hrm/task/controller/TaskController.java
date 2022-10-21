package com.son.hrm.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.son.hrm.common.utils.ResponseHelper;
import com.son.hrm.task.dto.TaskDTO;
import com.son.hrm.task.model.Task;
import com.son.hrm.task.service.TaskService;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public Object getAllTask() {
		List<Task> tasks = taskService.getAllTask();
		
		return ResponseHelper.getResponse(tasks, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public Object getTaskById(@PathVariable("id") Integer id) {
		
		Task task = taskService.findTaskById(id);
		
		if (task == null)
			return ResponseHelper.getErrorResponse("Task not found.", 
												HttpStatus.BAD_REQUEST);
	
		return ResponseHelper.getResponse(task, HttpStatus.OK);
	}
	
	@PostMapping("{groupId}/{userId}")
	public Object createTask(@PathVariable("groupId") Integer groupId,
							@PathVariable("userId") Integer userId,
								@RequestBody TaskDTO task, 
								BindingResult errors) {
		
		if (errors.hasErrors())
			return ResponseHelper.getErrorResponse(errors, 
													HttpStatus.BAD_REQUEST);
		
		Task taskCreation = taskService.createTask(groupId, userId, task);
		
		if (taskCreation == null)
			return ResponseHelper.getErrorResponse("Group or user not found.", 
													HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(taskCreation, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public Object updateTaskById(@RequestBody Task task, 
							BindingResult errors,
							@PathVariable("id") Integer id) {
		
		if (errors.hasErrors())
			return ResponseHelper.getErrorResponse(errors, 
											HttpStatus.BAD_REQUEST);
		
		Task taskUpdate = taskService.updateTaskById(id, task);
		
		if (taskUpdate == null)
			return ResponseHelper.getErrorResponse("Task not found.", 
												HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(task, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public Object deleteTaskById(@PathVariable("id") Integer id) {
		
		boolean isDeleted = taskService.deleteTaskById(id);
		
		if (!isDeleted) 
			return ResponseHelper.getErrorResponse("Delete failed! Task not found.", 
												HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getErrorResponse("Delete successfully!", 
												HttpStatus.OK);
	}
}
