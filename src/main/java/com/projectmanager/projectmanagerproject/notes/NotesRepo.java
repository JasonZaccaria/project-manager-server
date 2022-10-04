package com.projectmanager.projectmanagerproject.notes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NotesRepo extends JpaRepository<Notes, Long> {
    List<Notes> findAllByProjectId(Long projectId);

    List<Notes> findAllByProjectName(String projectName);

    List<Notes> findAllByOwner(String owner);

    Notes findById(long id);
}
