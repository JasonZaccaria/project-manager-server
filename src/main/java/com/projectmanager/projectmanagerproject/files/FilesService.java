package com.projectmanager.projectmanagerproject.files;

import java.util.List;

public interface FilesService {
    List<Files> getAllFiles();

    List<Files> getFilesWithId(Long projectId);

    List<Files> getFilesWithName(String projectName);

    List<Files> getFilesWithOwner(String owner);

    Files saveFile(Files file);
}
