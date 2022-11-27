package com.projectmanager.projectmanagerproject.project;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.projectmanager.projectmanagerproject.deadlines.DeadlineServiceImpl;
import com.projectmanager.projectmanagerproject.deadlines.Deadlines;
import com.projectmanager.projectmanagerproject.dto.CreateProjectRequest;
import com.projectmanager.projectmanagerproject.dto.GetProjectResponse;
import com.projectmanager.projectmanagerproject.files.Files;
import com.projectmanager.projectmanagerproject.files.FilesServiceImpl;
import com.projectmanager.projectmanagerproject.notes.Notes;
import com.projectmanager.projectmanagerproject.notes.NotesServiceImpl;
import com.projectmanager.projectmanagerproject.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServiceImpl projectServiceImpl;
    private final JwtUtil jwtUtil;
    private final NotesServiceImpl notesServiceImpl;
    private final DeadlineServiceImpl deadlineServiceImpl;
    private final FilesServiceImpl filesServiceImpl;

    @GetMapping("/projects")
    public ResponseEntity<?> getProjects(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
            String username = jwtUtil.getUsernameFromToken(decodedJWT);
            return ResponseEntity.ok().body(new GetProjectResponse(projectServiceImpl.getProjects(username)));
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/createproject")
    public ResponseEntity<Project> creatProject(@RequestBody CreateProjectRequest createProjectRequest,
            @RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
            String username = jwtUtil.getUsernameFromToken(decodedJWT);
            Project project = new Project(null, createProjectRequest.getProjectName(),
                    createProjectRequest.getCreationDate(), username);
            return ResponseEntity.ok().body(projectServiceImpl.saveProject(project));
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/viewproject")
    public ResponseEntity<?> viewProject(@RequestHeader("Authorization") String token, @RequestBody ProjectDataRequestDto projectDataRequestDto) {
        try {
            List<Notes> projectNotes = notesServiceImpl.getNotesWithId(projectDataRequestDto.getProjectId());
            List<Deadlines> projectDeadlines = deadlineServiceImpl.getDeadlinesWithId(projectDataRequestDto.getProjectId());
            List<Files> projectFiles = filesServiceImpl.getFilesWithId(projectDataRequestDto.getProjectId());
            return ResponseEntity.ok().body(new ProjectDataResponseDto(projectNotes, projectDeadlines, projectFiles));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}