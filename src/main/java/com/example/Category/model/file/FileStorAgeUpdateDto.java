package com.example.Category.model.file;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStorAgeUpdateDto {
    private Long id;
    private String name;
    private String extansion;

    private Long fileSize;
    private String hashId;
    private String contentType;
    private String uploadPath;

    private FileStorageStatus fileStorageStatus;

}
