package com.projectmanager.projectmanagerproject.notes;

import java.util.List;

public interface NotesService {
    List<Notes> getAllNotes();

    List<Notes> getNotesWithId(Long projectId);

    List<Notes> getNotesWithName(String projectName);

    List<Notes> getAllWithOwner(String owner);

    Notes saveNotes(Notes notes);
}
