package com.projectmanager.projectmanagerproject.notes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.projectmanager.projectmanagerproject.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NotesServiceImpl notesServiceImpl;
    private final JwtUtil jwtUtil;
    
    @PostMapping("/create")
    public ResponseEntity<?> createNote(@RequestHeader("Authorization") String token, @RequestBody CreateNoteReqDto createNoteReqDto) {
        try {
            String jwt = token.substring(7);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
            String owner = jwtUtil.getUsernameFromToken(decodedJWT);
            Notes newNote = new Notes(null, createNoteReqDto.getProjectId(), owner, createNoteReqDto.getProjectName(), createNoteReqDto.getNoteName(), createNoteReqDto.getNote(), createNoteReqDto.getDate());
            notesServiceImpl.saveNotes(newNote);
            return ResponseEntity.ok().body(new CreateNoteResDto(true));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
