package com.projectmanager.projectmanagerproject.files;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateFileReqDto {
    private Long projectId;
    private String projectName;
    private String noteName;
    private String note;
    private Date date;
}
