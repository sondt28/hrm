package com.son.hrm.group.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.hrm.group.model.Group;
import com.son.hrm.group.repository.GroupRepository;
import com.son.hrm.task.model.Task;
import com.son.hrm.user.model.User;
import com.son.hrm.user.repository.UserRepository;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Group> getAllGroup() {
		
		return groupRepository.findAll();
	}

	@Override
	public Group findGroupById(Integer id) {
	
		Optional<Group> groupOpt = groupRepository.findById(id);
		
		if (groupOpt.isEmpty())
			return null;
		
		Group groupCurrent = groupOpt.get();
		
		return groupCurrent;
	}

	@Override
	public Group createGroup(Group group) {
		
		return groupRepository.save(group);
	}

	@Override
	public Group updateGroupById(Integer id, Group group) {
		
		Optional<Group> groupOpt = groupRepository.findById(id);
		
		if (groupOpt.isEmpty())
			return null;
		
		Group groupCurrent = groupOpt.get();
		groupCurrent.setName(group.getName());
		groupCurrent.setDescription(group.getDescription());
		groupCurrent.setStartDate(group.getStartDate());
		groupCurrent.setEndDate(group.getEndDate());
		groupCurrent.setUsers(group.getUsers());
		groupCurrent.setTasks(group.getTasks());
		
		groupRepository.save(groupCurrent);
		
		return groupCurrent;
	}

	@Override
	public boolean deleteGroupById(Integer id) {
		Optional<Group> groupOpt = groupRepository.findById(id);
		
		if (groupOpt.isEmpty())
			return false;
		
		groupRepository.deleteById(id);
		
		return true;
	}

	@Override
	public Group addUserToGroup(Integer groupId, Integer userId) {
		
		Optional<Group> groupOpt = groupRepository.findById(groupId);
		Optional<User> userOpt = userRepository.findById(userId);
		
		if (groupOpt.isEmpty() || userOpt.isEmpty())
			return null;
		
		Group groupCurrent = groupOpt.get();
		User userCurrent = userOpt.get();
		
		groupCurrent.addUser(userCurrent);
		
		groupRepository.save(groupCurrent);
		
		return groupCurrent;
	}

	@Override
	public Group removeUserFromGroup(Integer groupId, Integer userId) {
		
		Optional<Group> groupOpt = groupRepository.findById(groupId);
		Optional<User> userOpt = userRepository.findById(userId);
		
		if (groupOpt.isEmpty() || userOpt.isEmpty())
			return null;
		
		Group groupCurrent = groupOpt.get();
		User userCurrent = userOpt.get();
		
		groupCurrent.removeUser(userCurrent);
		
		System.out.println(userCurrent.getTasks());
		
//		userCurrent.getTasks().forEach(t -> userCurrent.removeTask(t));
		
		userCurrent.removeAllTask(userCurrent.getTasks());
		
		System.out.println(userCurrent.getTasks());
		
		userRepository.save(userCurrent);
		
		return groupRepository.save(groupCurrent);
	}
}
