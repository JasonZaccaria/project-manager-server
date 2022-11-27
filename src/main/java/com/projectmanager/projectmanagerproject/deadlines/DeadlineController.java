package com.projectmanager.projectmanagerproject.deadlines;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.projectmanager.projectmanagerproject.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/deadlines")
@RequiredArgsConstructor
public class DeadlineController {
    
    private final DeadlineServiceImpl deadlineServiceImpl;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createDeadline(@RequestHeader("Authorization") String token, @RequestBody DeadlineReqDto deadlineReqDto) {
        try {
            String jwt = token.substring(7);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
            String owner = jwtUtil.getUsernameFromToken(decodedJWT);
            Deadlines deadline = new Deadlines(null, deadlineReqDto.getProjectId(), owner, deadlineReqDto.getProjectName(),
                    deadlineReqDto.getDeadlineNote(), deadlineReqDto.getDeadlineDate());
            deadlineServiceImpl.saveDeadline(deadline);
            return ResponseEntity.ok().body(new DeadlineResDto(true));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getDeadlines(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
            String owner = jwtUtil.getUsernameFromToken(decodedJWT);
            return ResponseEntity.ok().body(deadlineServiceImpl.getDeadlinesWithOwner(owner));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
