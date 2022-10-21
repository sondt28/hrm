package com.son.hrm.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.son.hrm.group.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
	
}
