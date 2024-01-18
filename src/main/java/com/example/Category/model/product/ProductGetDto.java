package com.example.Category.model.product;


import com.example.Category.model.basic.CategorySaveDto;
import com.example.Category.model.file.FileStoreAgeSave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGetDto {
    private Long id;
    private String description;
    private String title;
    private CategorySaveDto category;
//    private Long categoryId;
    private String color;
    private FileStoreAgeSave fileStorAge;
//    private Long fileId;


}
