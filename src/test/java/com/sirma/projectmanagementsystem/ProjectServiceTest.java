package com.sirma.projectmanagementsystem;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sirma.projectmanagementsystem.exceptionHandeler.NotFoundException;
import com.sirma.projectmanagementsystem.model.Project;
import com.sirma.projectmanagementsystem.repository.ProjectRepository;
import com.sirma.projectmanagementsystem.service.ProjectService;

public class ProjectServiceTest {

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProject() {
        Project project = new Project();
        when(projectRepository.save(project)).thenReturn(project);

        String result = projectService.addProject(project);

        assertEquals("Project added successfully", result);
    }

    @Test
    public void testGetAllProjects() {
        List<Project> projects = new ArrayList<>();
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertEquals(projects, result);
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project();
        project.setId(1);
        when(projectRepository.findById(1)).thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(1);

        assertEquals(project, result);
    }

    @Test
    public void testGetProjectByIdNotFound() {
        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            projectService.getProjectById(1);
        });
    }

    @Test
    public void testUpdateAnProject() {
        Project existingProject = new Project();
        existingProject.setId(1);
        existingProject.setName("Old Name");

        Project updatedProject = new Project();
        updatedProject.setName("New Name");

        when(projectRepository.findById(1)).thenReturn(Optional.of(existingProject));

        String result = projectService.updateAnProject(1, updatedProject);

        assertEquals("Project successfully updated", result);
        assertEquals(updatedProject.getName(), existingProject.getName());
    }

    @Test
    public void testDeleteProjectById() {
        when(projectRepository.findById(1)).thenReturn(Optional.of(new Project()));

        String result = projectService.deleteProjectById(1);

        assertEquals("Data has been deleted successfully", result);
        verify(projectRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteProjectByIdNotFound() {
        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            projectService.deleteProjectById(1);
        });
    }

    @Test
    public void testDeleteAllProject() {
        String result = projectService.deleteAllProject();

        assertEquals("All projects have been deleted", result);
        verify(projectRepository, times(1)).deleteAll();
    }
}
