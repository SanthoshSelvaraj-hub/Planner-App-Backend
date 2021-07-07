 package com.planner.rest.webservices.restfulwebservices.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskHardcodedService {
	
	private static List<Task> tasks = new ArrayList();
	private static long idCounter = 0;
	
	static {
		tasks.add(new Task(++idCounter, "admin", "Learn react", new Date(), false));
		tasks.add(new Task(++idCounter, "admin", "Apply for jobs", new Date(), false));
		tasks.add(new Task(++idCounter, "admin", "HackerEarth Challenges", new Date(), false));
	}
	
	public List<Task> findAll(){
		return tasks;
	}
	
	public Task saveTask(Task task) {
		if(task.getId()==-1 || task.getId()==0) {
			task.setId(++idCounter);
			tasks.add(task);
			
		}
		else {
			deleteById(task.getId());
			tasks.add(task);
		}	
		return task;
	}
	
	public Task deleteById(long id) {
		Task task = findById(id);
		if(task==null)return null;
		if(tasks.remove(task)) {
			return task;
		}
		
		return null;
	}

	public Task findById(long id) {
		for(Task task: tasks) {
			if(task.getId()==id) {
				return task;
			}
		}
		return null;
	}
}
