package com.projectmanager.projectmanagerproject.deadlines;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeadlineReqDto {
    private Long projectId;
    private String projectName;
    private String deadlineNote;
    private Date deadlineDate;
}
