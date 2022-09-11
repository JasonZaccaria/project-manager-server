package com.projectmanager.projectmanagerproject.dto;

import java.util.Date;
import java.util.List;

import com.projectmanager.projectmanagerproject.project.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProjectResponse {
    /*private Long id;
    private String projectName;
    private Date creationDate;
    private String owner;*/
    private List<Project> projects;
    
}
