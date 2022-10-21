package com.son.hrm.group.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.son.hrm.task.model.Task;
import com.son.hrm.user.model.User;

@Entity
@Table(name = "`group`")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate; 

	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST, CascadeType.REFRESH},
			   mappedBy = "group",
			   fetch = FetchType.LAZY)
	private Set<User> users;

	@OneToMany(fetch = FetchType.LAZY,
			   mappedBy = "group",
			   cascade = CascadeType.ALL)
	private List<Task> tasks;
	
	public Group() {
		super();
	}

	public Group(int id, String name, String description, Date startDate, Date endDate, Set<User> users,
			List<Task> tasks) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.users = users;
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addUser(User user) {
		if (users == null)
			users = new HashSet<>();
		
		users.add(user);
		user.setGroup(this);
	}
	
	public void removeUser(User user) {
		users.remove(user);
		
		user.setGroup(null);
	}
	
	public void addTask(Task task) {
		if (tasks == null)
			tasks = new ArrayList<>();
		
		tasks.add(task);
		task.setGroup(this);
	}
}
