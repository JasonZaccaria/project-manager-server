package com.projectmanager.projectmanagerproject.deadlines;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DeadlineRepo extends JpaRepository<Deadlines, Long> {
    /*Deadlines findByProjectId(Long id);
    
    Deadlines findByProjectName(String projectName);*/
    Deadlines findById(long id);

    List<Deadlines> findAllByProjectId(long id);

    //Deadlines findByProjectName(String projectName);

    List<Deadlines> findAllByProjectName(String projectName);
}
