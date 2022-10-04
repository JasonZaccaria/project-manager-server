package com.projectmanager.projectmanagerproject.files;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {

    private final FilesRepo filesRepo;
    
    @Override
    public List<Files> getAllFiles() {
        return filesRepo.findAll();
    }

    @Override
    public List<Files> getFilesWithId(Long projectId) {
        return filesRepo.findAllByProjectId(projectId);
    }

    @Override
    public List<Files> getFilesWithName(String projectName) {
        return filesRepo.findAllByProjectName(projectName);
    }

    @Override
    public List<Files> getFilesWithOwner(String owner) {
        return filesRepo.findAllByOwner(owner);
    }

    @Override
    public Files saveFile(Files file) {
        return filesRepo.save(file);
    }
    
}
