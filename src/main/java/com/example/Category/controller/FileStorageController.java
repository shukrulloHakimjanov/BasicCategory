package com.example.Category.controller;

import com.example.Category.model.file.FileStorAge;
import com.example.Category.model.user.auth.AuthenticationRequest;
import com.example.Category.model.user.auth.AuthenticationResponse;
import com.example.Category.model.user.auth.RegisterRequest;
import com.example.Category.service.CategoryService;
import com.example.Category.service.FileStorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class FileStorageController {
    private final FileStorageService fileStorageSevice;


    @Value("${upload.folder}")
    private String uploadFolder;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestPart MultipartFile multipartFile){
        log.info("REST request to delete Post ");
        fileStorageSevice.save(multipartFile);
        return ResponseEntity.ok(multipartFile.getOriginalFilename()+" file is save");
    }
    @GetMapping
    public List<FileStorAge> getAll()
    {
        log.info("REST request to get All");
        return fileStorageSevice.findAll();
    }

    @DeleteMapping("/{hashId}")
    public ResponseEntity<String>delete(@PathVariable String hashId){
        log.info("REST request to delete hashId {}",hashId);
        fileStorageSevice.delete(hashId);
        return ResponseEntity.ok("file is delete");
    }

}
