package com.son.hrm.task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.son.hrm.task.dto.TaskDTO;
import com.son.hrm.task.model.Task;

@Mapper
public interface TaskMapper {
	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
	
	Task toModel(TaskDTO dto);
	TaskDTO toDTO(Task task);
}
