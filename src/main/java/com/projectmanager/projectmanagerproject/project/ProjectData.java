package com.projectmanager.projectmanagerproject.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Remember I need to make most of these attributes optional

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String projectName;
    private String notes; //will be straight up text that users submit via form and is taken and stored need char limit
    private String goals;
    private String files; //will be file locations


    
}
