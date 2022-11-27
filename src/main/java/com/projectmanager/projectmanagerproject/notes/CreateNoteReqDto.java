package com.projectmanager.projectmanagerproject.notes;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateNoteReqDto {
    private Long projectId;
    private String projectName;
    private String noteName;
    private String note;
    private Date date;
}
