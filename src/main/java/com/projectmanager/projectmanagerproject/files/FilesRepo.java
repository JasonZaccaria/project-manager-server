package com.projectmanager.projectmanagerproject.files;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepo extends JpaRepository<Files, Long> {
    List<Files> findAllByProjectId(Long projectId);

    List<Files> findAllByProjectName(String projectName);

    Files findById(long id);

}
