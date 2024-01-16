package com.example.Category.model.file;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.lang.String;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class FileStorAge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String extansion;

    private Long fileSize;
    private String hashId;
    private String contentType;
    private String uploadPath;
    @Enumerated(EnumType.STRING)
    private FileStorageStatus fileStorageStatus;



}
