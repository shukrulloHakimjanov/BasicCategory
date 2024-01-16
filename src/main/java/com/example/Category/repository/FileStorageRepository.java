package com.example.Category.repository;

import com.example.Category.model.file.FileStorAge;
import com.example.Category.model.file.FileStorageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileStorageRepository extends JpaRepository<FileStorAge ,Long> {
    FileStorAge findByHashId(String hashid);

    List<FileStorAge> findAllByFileStorageStatus(FileStorageStatus status);

}
