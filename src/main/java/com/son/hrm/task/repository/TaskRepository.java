package com.son.hrm.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.son.hrm.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
