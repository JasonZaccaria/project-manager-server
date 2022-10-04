package com.projectmanager.projectmanagerproject.notes;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotesRepo notesRepo;

    @Override
    public List<Notes> getAllNotes() {
        return notesRepo.findAll();
    }

    @Override
    public List<Notes> getNotesWithId(Long projectId) {
        return notesRepo.findAllByProjectId(projectId);
    }

    @Override
    public List<Notes> getNotesWithName(String projectName) {
        return notesRepo.findAllByProjectName(projectName);
    }

    @Override
    public List<Notes> getAllWithOwner(String owner) {
        return notesRepo.findAllByOwner(owner);
    }

    @Override
    public Notes saveNotes(Notes notes) {
        return notesRepo.save(notes);
    }
    
}
