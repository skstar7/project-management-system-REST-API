package com.sirma.projectmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sirma.projectmanagementsystem.model.Project;
import com.sirma.projectmanagementsystem.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	@PostMapping
	public String addProject(@Valid @RequestBody Project project) {
		
	return	projectService.addProject(project);
				
	}
	
	@GetMapping
	public List<Project> getAllProjects() {
		
		return projectService.getAllProjects();
	
	}
	
	@GetMapping("/id")	
	public Project  getProjectById(@RequestParam int id){
		
		return projectService.getProjectById(id);
		
	}
		
	
	@PatchMapping
	public String updateAnProject(@RequestParam int id , @Valid @RequestBody Project project) {
		
		return projectService.updateAnProject(id,project);
 		
	}

	@DeleteMapping("/id")
	public String deleteProjectById(@RequestParam int id) {
		
		return projectService.deleteProjectById(id);
		
	}
	
	@DeleteMapping
	public String deleteAllProject() {
		
		return projectService.deleteAllProject();
		
		
	}
	
	
	
}
