package com.sirma.projectmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirma.projectmanagementsystem.model.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer >{

}
