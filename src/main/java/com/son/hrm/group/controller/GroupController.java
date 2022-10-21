package com.son.hrm.group.controller;

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
import com.son.hrm.group.model.Group;
import com.son.hrm.group.repository.GroupRepository;
import com.son.hrm.group.service.GroupService;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping
	public Object getAllGroup() {
		List<Group> groups = groupService.getAllGroup();
		
		return ResponseHelper.getResponse(groups, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public Object getGroupById(@PathVariable("id") Integer id) {
		
		Group group = groupService.findGroupById(id);
		
		if (group == null) 
			return ResponseHelper.getErrorResponse("Group not found", 
											HttpStatus.BAD_REQUEST);
	
		return ResponseHelper.getResponse(group, HttpStatus.OK);
	}
	
	@PostMapping("/add-user/{groupId}/{userId}")
	public Object addUserToGroup(@PathVariable("groupId") Integer groupId,
								@PathVariable("userId") Integer userId) {
		
		Group group = groupService.addUserToGroup(groupId, userId);
		
		if (group == null)
			return ResponseHelper.getErrorResponse("User or group not found. ", 
													HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(group, HttpStatus.OK);
	}
	
	@PostMapping("/remove-user/{groupId}/{userId}")
	public Object removeUserFromGroup(
								@PathVariable("groupId") Integer groupId,
								@PathVariable("userId") Integer userId) {
		
		Group group = groupService.removeUserFromGroup(groupId, userId);
		
		if (group == null)
			return ResponseHelper.getErrorResponse("User or group not found. ", 
					HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(group, HttpStatus.OK);
	}
	
	@PostMapping
	public Object createGroup(@RequestBody Group group,
							BindingResult errors) {
		
		if (errors.hasErrors())
			return ResponseHelper.getErrorResponse(errors, HttpStatus.BAD_REQUEST);
		
		Group groupCreate = groupService.createGroup(group);
		
		return ResponseHelper.getResponse(groupCreate, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public Object updateGroupById(@PathVariable("id") Integer id,
								@RequestBody Group group,
								BindingResult errors) {
		
		if (errors.hasErrors())
			return ResponseHelper.getErrorResponse(errors, HttpStatus.BAD_REQUEST);
			
		Group groupUpdate = groupService.updateGroupById(id, group);
		
		if (groupUpdate == null)
			return ResponseHelper.getErrorResponse("Group not found.", 
												HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(groupUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public Object deleteGroupById(@PathVariable("id") Integer id) {
		boolean isDeleted = groupService.deleteGroupById(id);
		
		if (!isDeleted)
			return ResponseHelper.getErrorResponse("Delete failed! Group not found.", 
												HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse("Delete successfully !", HttpStatus.OK);
	}
}
