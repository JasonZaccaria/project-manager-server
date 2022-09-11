package com.projectmanager.projectmanagerproject.project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.projectmanager.projectmanagerproject.dto.CreateProjectRequest;
import com.projectmanager.projectmanagerproject.dto.GetProjectResponse;
import com.projectmanager.projectmanagerproject.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServiceImpl projectServiceImpl;
    private final JwtUtil jwtUtil;

    @GetMapping("/projects")
    public ResponseEntity<GetProjectResponse> getProjects(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
        String username = jwtUtil.getUsernameFromToken(decodedJWT);
        return ResponseEntity.ok().body(new GetProjectResponse(projectServiceImpl.getProjects(username)));
    }

    @PostMapping("/createproject")
    public ResponseEntity<Project> creatProject(@RequestBody CreateProjectRequest createProjectRequest, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
        String username = jwtUtil.getUsernameFromToken(decodedJWT);
        Project project = new Project(null, createProjectRequest.getProjectName(),
                createProjectRequest.getCreationDate(), username);
        return ResponseEntity.ok().body(projectServiceImpl.saveProject(project));
    }
    
}
