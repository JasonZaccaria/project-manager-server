package com.projectmanager.projectmanagerproject.deadlines;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeadlineServiceImpl implements DeadlineService {

    private final DeadlineRepo deadlineRepo;

    @Override
    public Deadlines saveDeadline(Deadlines deadline) {
        return deadlineRepo.save(deadline);
    }

    @Override
    public List<Deadlines> getDeadlinesWithId(Long projectId) {
        return deadlineRepo.findAllByProjectId(projectId);
    }

    @Override
    public List<Deadlines> getDeadlinesWithName(String projectName) {
        return deadlineRepo.findAllByProjectName(projectName);
    }

    @Override
    public List<Deadlines> getDeadlinesWithOwner(String owner) {
        return deadlineRepo.findAllByOwner(owner);
    }

    @Override
    public List<Deadlines> getAllDeadlines() {
        return deadlineRepo.findAll();
    }
    
}
