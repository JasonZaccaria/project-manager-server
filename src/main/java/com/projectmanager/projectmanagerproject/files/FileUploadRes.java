package com.projectmanager.projectmanagerproject.files;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadRes {

    private String fileName;
    private String fileUrl;
    
}
