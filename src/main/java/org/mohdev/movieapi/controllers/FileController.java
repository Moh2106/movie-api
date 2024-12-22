package org.mohdev.movieapi.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.mohdev.movieapi.service.file.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;


    @Value("${project.poster}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws IOException {
        String filename = fileService.uploadFile(path, file);
        return ResponseEntity.ok("File upload " +filename);
    }

    @GetMapping("/{filename}")
    public void serveFileHandler(@PathVariable String filename, HttpServletResponse response) throws IOException {
        InputStream ressource = fileService.getRessource(path, filename);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(ressource, response.getOutputStream());
    }
}
