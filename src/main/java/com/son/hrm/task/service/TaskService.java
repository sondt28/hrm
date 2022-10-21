package com.son.hrm.task.service;

import java.util.List;

import com.son.hrm.task.dto.TaskDTO;
import com.son.hrm.task.model.Task;

public interface TaskService {
	Task createTask(Integer groupId, Integer userId, TaskDTO task);
	Task updateTaskById(Integer id, Task task);
	List<Task> getAllTask();
	Task findTaskById(Integer id);
	boolean deleteTaskById(Integer id);
}
