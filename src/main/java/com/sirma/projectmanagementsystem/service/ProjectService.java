package com.sirma.projectmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirma.projectmanagementsystem.exceptionHandeler.NotFoundException;
import com.sirma.projectmanagementsystem.model.Project;
import com.sirma.projectmanagementsystem.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	public String addProject(Project project) {

		projectRepository.save(project);

		return "Project added succesfully";
	}

	public List<Project> getAllProjects() {

		return projectRepository.findAll();

	}

	public Project getProjectById(int id) {

		Optional<Project> projects = projectRepository.findById(id);

		return projects.get();
	}

	public String updateAnProject(int id, Project project) {

		Optional<Project> optionalProject = projectRepository.findById(id);
		if (optionalProject.isPresent()) {
			Project existingProject = optionalProject.get();

			if (project.getId() != 0)
				existingProject.setId(project.getId());
			if (project.getName() != null)
				existingProject.setName(project.getName());
			if (project.getDescription() != null)
				existingProject.setDescription(project.getDescription());
			if (project.getStartDate() != null)
				existingProject.setStartDate(project.getStartDate());
			if (project.getEndDate() != null)
				existingProject.setEndDate(project.getEndDate());

			projectRepository.save(existingProject);
			return "Project successfully updated";
		} else {
			throw new NotFoundException("Project with ID " + id + " not found");
		}

	}
	

	public String deleteProjectById(int id) {

		Optional<Project> optionalProject = projectRepository.findById(id);
		if (optionalProject.isPresent()) {
			projectRepository.deleteById(id);
			return "Data has been deleted succesfully";
		}else {
			throw new NotFoundException("Project with ID " + id + " not found");
		}
	}

	public String deleteAllProject() {
		projectRepository.deleteAll();
		return "All project have been deleted";

	}

}
