package com.example.Category.model.file;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStoreAgeSave {
    private String name;
    private String extansion;
    private Long fileSize;
    private String hashId;
    private String contentType;
    private String uploadPath;
    private FileStorageStatus fileStorageStatus;

}
