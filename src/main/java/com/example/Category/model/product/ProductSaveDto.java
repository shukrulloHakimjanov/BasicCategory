package com.example.Category.model.product;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSaveDto {
    private String title;
    private String description;
//    private Category getname;
    private Long categoryId;
    private String color;
//    private FileStorAge fileStorAge;
    private Long fileId;


}
