package com.projectmanager.projectmanagerproject.files;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqFileDto {
    private Long projectId;
    private String projectName;
    private String fileName;
    private String fileLocation;
    private Date fileUploadDate;
}
