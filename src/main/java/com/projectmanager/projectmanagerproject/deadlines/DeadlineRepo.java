package com.projectmanager.projectmanagerproject.deadlines;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DeadlineRepo extends JpaRepository<Deadlines, Long> {

    Deadlines findById(long id);

    List<Deadlines> findAllByProjectId(long id);

    List<Deadlines> findAllByProjectName(String projectName);

    List<Deadlines> findAllByOwner(String owner);
}
