package com.example.Category.model.product;


import com.example.Category.model.basic.Category;
import com.example.Category.model.file.FileStorAge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "productTitle")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "color")
    private String color;

    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private Category category;
    @Column(name = "file_id")
    private Long fileId;
    @ManyToOne
    @JoinColumn(name = "file_id",insertable = false,updatable = false)
    private FileStorAge fileStorAge;


}