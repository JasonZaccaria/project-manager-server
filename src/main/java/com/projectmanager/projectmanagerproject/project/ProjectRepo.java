package com.projectmanager.projectmanagerproject.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    List<Project> findByOwner(String owner);

    Project findById(long id);   
}
