package com.projectmanager.projectmanagerproject.dto;

import java.util.List;

import com.projectmanager.projectmanagerproject.project.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProjectResponse {

    private List<Project> projects;
    
}
