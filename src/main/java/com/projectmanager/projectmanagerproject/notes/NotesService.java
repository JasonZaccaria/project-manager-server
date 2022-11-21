package com.projectmanager.projectmanagerproject.notes;

import java.util.List;
import java.util.Optional;

public interface NotesService {
    List<Notes> getAllNotes();

    List<Notes> getNotesWithId(Long projectId);

    List<Notes> getNotesWithName(String projectName);

    List<Notes> getAllWithOwner(String owner);

    Notes saveNotes(Notes notes);

    Optional<Notes> getNoteWithId(Long notesId);

    boolean deleteNote(Long notesId);
}
