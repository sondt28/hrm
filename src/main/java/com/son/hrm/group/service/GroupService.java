package com.son.hrm.group.service;

import java.util.List;

import com.son.hrm.group.model.Group;
import com.son.hrm.user.model.User;

public interface GroupService {
	List<Group> getAllGroup();
	Group findGroupById(Integer id);
	Group createGroup(Group group);
	Group updateGroupById(Integer id, Group group);
	boolean deleteGroupById(Integer id);
	Group addUserToGroup(Integer groupId, Integer userId);
	Group removeUserFromGroup(Integer groupId, Integer userId);
}
