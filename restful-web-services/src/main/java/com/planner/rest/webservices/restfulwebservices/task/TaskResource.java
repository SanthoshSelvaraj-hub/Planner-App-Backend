package com.planner.rest.webservices.restfulwebservices.task;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TaskResource {
	
	@Autowired
	private TaskHardcodedService taskService;
	
	@GetMapping ("/users/{username}/tasks")
	public List<Task> getAllTasks(@PathVariable String username){
		return taskService.findAll();
	}
	
	@GetMapping ("/users/{username}/tasks/{id}")
	public Task getTask(@PathVariable String username, @PathVariable long id){
		return taskService.findById(id);
	}
	
	@PutMapping ("/users/{username}/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable String username, @PathVariable long id, @RequestBody Task task){
		Task taskUpdated = taskService.saveTask(task);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@PostMapping ("/users/{username}/tasks")
	public ResponseEntity<Task> updateTask(@PathVariable String username, @RequestBody Task task){
		Task createdTask = taskService.saveTask(task);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(createdTask.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/users/{username}/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable String username, @PathVariable long id){
		Task task = taskService.deleteById(id);
		if(task!=null) {
			return ResponseEntity.noContent().build();
		} 
		return ResponseEntity.notFound().build();
	}
}
