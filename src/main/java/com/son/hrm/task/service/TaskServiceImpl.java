package com.son.hrm.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.hrm.group.model.Group;
import com.son.hrm.group.repository.GroupRepository;
import com.son.hrm.task.dto.TaskDTO;
import com.son.hrm.task.mapper.TaskMapper;
import com.son.hrm.task.model.Task;
import com.son.hrm.task.repository.TaskRepository;
import com.son.hrm.user.model.User;
import com.son.hrm.user.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Override
	public Task createTask(Integer groupId, Integer userId, TaskDTO task) {
		
		Optional<Group> groupOpt = groupRepository.findById(groupId);
		Optional<User> userOpt = userRepository.findById(userId);
		
		if (groupOpt.isEmpty() || userOpt.isEmpty())
			return null;
		
		Group group = groupOpt.get();
		User user = userOpt.get();
		
		if (user.getGroup().getId() != group.getId())
			return null;
		
		Task taskCreation = TaskMapper.INSTANCE.toModel(task);
		taskCreation.setUser(user);
		taskCreation.setGroup(group);
		
		repository.save(taskCreation);
		
		return taskCreation;
	}

	@Override
	public Task updateTaskById(Integer id, Task task) {
		Optional<Task> taskOpt = repository.findById(id);
		
		if (taskOpt.isEmpty())
			return null;
		
		Task taskCurrent = taskOpt.get();
		taskCurrent.setName(task.getName());
		taskCurrent.setDescription(task.getDescription());
		taskCurrent.setStartDate(task.getStartDate());
		taskCurrent.setEndDate(task.getEndDate());
		taskCurrent.setUser(task.getUser());
		
		repository.save(taskCurrent);
		
		return taskCurrent;
	}

	@Override
	public List<Task> getAllTask() {
		
		return repository.findAll();
	}

	@Override
	public Task findTaskById(Integer id) {
		Optional<Task> taskOpt = repository.findById(id);
		
		if (taskOpt.isEmpty())
			return null;
		
		Task task = taskOpt.get();
		
		return task;
	}

	@Override
	public boolean deleteTaskById(Integer id) {
		Optional<Task> taskOpt = repository.findById(id);
		
		if (taskOpt.isEmpty())
			return false;
		
		repository.deleteById(id);
		
		return true;
	}

}
