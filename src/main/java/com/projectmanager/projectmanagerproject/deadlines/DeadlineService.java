package com.projectmanager.projectmanagerproject.deadlines;

import java.util.List;

public interface DeadlineService {

    Deadlines saveDeadline(Deadlines deadline);

    List<Deadlines> getDeadlinesWithId(Long projectId);

    List<Deadlines> getDeadlinesWithName(String projectName);

    List<Deadlines> getDeadlinesWithOwner(String owner);

    List<Deadlines> getAllDeadlines();
    
}
