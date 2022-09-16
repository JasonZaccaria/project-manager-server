package com.projectmanager.projectmanagerproject.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDataRequestDto {
    private Long projectId;
    private String projectName;
}
