package com.projectmanager.projectmanagerproject.notes;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Notes> getNoteWithId(Long notesId) {
        return notesRepo.findById(notesId);
    }
    
    @Override
    public boolean deleteNote(Long noteId) {
        try {
            Optional<Notes> note = notesRepo.findById(noteId);
            notesRepo.delete(note.get());
            return true;
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            return false;
        }
    }

}
