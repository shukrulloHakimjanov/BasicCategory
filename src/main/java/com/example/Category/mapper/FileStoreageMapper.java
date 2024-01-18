package com.example.Category.mapper;

import com.example.Category.model.basic.Category;
import com.example.Category.model.basic.CategoryGetDto;
import com.example.Category.model.file.FileStorAge;
import com.example.Category.model.file.FileStorAgeGetDto;
import com.example.Category.model.file.FileStoreAgeSave;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileStoreageMapper {
    FileStorAge toEnt(FileStoreAgeSave fileStoreAgeSave);
    FileStorAgeGetDto toDto(FileStorAge fileStorAge);

//     List<FileStorAgeGetDto> toDto(List<FileStorAge> fileStorAges);
}
