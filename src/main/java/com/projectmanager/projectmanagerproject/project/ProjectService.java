package com.projectmanager.projectmanagerproject.project;

import java.util.List;

public interface ProjectService {

    Project saveProject(Project project);

    Project getProject(long id);

    List<Project> getProjects(String owner);
    
}
