package com.projectmanager.projectmanagerproject.files;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.projectmanager.projectmanagerproject.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files/")
@RequiredArgsConstructor
public class FilesController {

    private final FilesServiceImpl filesServiceImpl;
    private final FileStorageService fileStorageService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createFile(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token, @RequestParam("id") Long id, @RequestParam("project") String project, @RequestParam("date") String date) {
        try {
            String jwt = token.substring(7);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
            String owner = jwtUtil.getUsernameFromToken(decodedJWT);
            Optional<FileUploadRes> uploadFileAttempt = fileStorageService.uploadFile(file);
            if (uploadFileAttempt.isPresent()) {
                System.out.println(file.getOriginalFilename());
                Long dateLong = Long.parseLong(date);
                filesServiceImpl.saveFile(new Files(null, id, owner, project, uploadFileAttempt.get().getFileName(), uploadFileAttempt.get().getFileUrl(), new Date(dateLong)));
                return ResponseEntity.ok().body(new ResFileDto(true));

            } else {
                return ResponseEntity.badRequest().build();
            }           
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteNote(@RequestHeader("Authorization") String token, @RequestBody Long id) {
        try {
            String jwt = token.substring(7);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
            String owner = jwtUtil.getUsernameFromToken(decodedJWT);
            return ResponseEntity.ok().body(filesServiceImpl.deleteFile(id));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
