package com.projectmanager.projectmanagerproject.project;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;

    @Override
    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public Project getProject(long id) {
        // TODO Auto-generated method stub
        return projectRepo.findById(id);
    }

    @Override
    public List<Project> getProjects(String owner) {
        return projectRepo.findByOwner(owner);
    }
    
}
