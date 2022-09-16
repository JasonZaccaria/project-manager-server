package com.projectmanager.projectmanagerproject.project;

import java.util.List;

import com.projectmanager.projectmanagerproject.deadlines.Deadlines;
import com.projectmanager.projectmanagerproject.files.Files;
import com.projectmanager.projectmanagerproject.notes.Notes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDataResponseDto {

    private List<Notes> notes;
    private List<Deadlines> deadlines;
    private List<Files> files;
    
}
