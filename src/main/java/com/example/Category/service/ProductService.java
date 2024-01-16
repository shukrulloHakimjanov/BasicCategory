package com.example.Category.service;


import com.example.Category.model.basic.Category;
import com.example.Category.model.basic.CategorySaveDto;
import com.example.Category.model.file.FileStorAge;
import com.example.Category.model.file.FileStorageDto;
import com.example.Category.model.product.ProductSaveDto;
import com.example.Category.model.product.ProductEntity;
import com.example.Category.model.product.ProductGetDto;
import com.example.Category.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<ProductEntity> getAll() {
        List<ProductEntity> pro = productRepository.findAll();
        //TODO map to dto
        return pro;
    }

//
//    public ProductUpdateDto save(ProductDto productDto) {
//        ProductEntity productEntity = fromDto(productDto);
//        ProductEntity save = productRepository.save(productEntity);
//        return new ProductUpdateDto(save.getId(), save.getTitle(),save.getDescription(),toDto(save.getCategory()), save.getColor());
//    }
//
//    private ProductEntity fromDto(ProductDto productDto) {
//        ProductEntity productEntity = new ProductEntity();
//        productEntity.setTitle(productDto.getTitle());
//        productEntity.setDescription(productDto.getDescription());
//        productEntity.setCategoryId(productDto.getCategoryId());
////        productEntity.setCategory(productDto.getGetname());
//        productEntity.setColor(productDto.getColor());
////        productEntity.setFileId(productDto.getFileId());
//        productEntity.setFileId(productDto.getFileId());
//        return productEntity;
//    }

    public ProductGetDto save(ProductSaveDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle(productDto.getTitle());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setColor(productDto.getColor());
        productEntity.setCategoryId(productDto.getCategoryId());
        productEntity.setFileId(productDto.getFileId());
        ProductEntity save = productRepository.save(productEntity);
        return toDto1(save);
    }



    public void delete(Long id){
        ProductEntity employee=productRepository.getOne(id);
        productRepository.delete(employee);
    }

//
    public ProductGetDto update(Long id, ProductSaveDto productDto) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        productEntity.setTitle(productDto.getTitle());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setCategoryId(productDto.getCategoryId());
        productEntity.setColor(productDto.getColor());
        productEntity.setFileId(productDto.getFileId());
        ProductEntity save = productRepository.save(productEntity);
        return toDto1(save);
    }

    private ProductGetDto toDto1(ProductEntity save) {
        return new ProductGetDto(save.getId(), save.getTitle(), save.getDescription(), toDto(save.getCategory()), save.getColor(),toDto2(save.getFileStorAge()));
    }

    private FileStorageDto toDto2(FileStorAge fileStorAge) {
        FileStorageDto dto =new FileStorageDto();
        dto.setName(fileStorAge.getName());
        dto.setContentType(fileStorAge.getContentType());
        dto.setUploadPath(fileStorAge.getUploadPath());
        dto.setExtansion(fileStorAge.getExtansion());
        dto.setHashId(fileStorAge.getHashId());
        dto.setFileStorageStatus(fileStorAge.getFileStorageStatus());
        return dto;
    }

    private CategorySaveDto toDto(Category category) {
        if (category != null) {
            CategorySaveDto dto = new CategorySaveDto();
            dto.setName(category.getName());
            return dto;
        }
        return null; // or throw an exception, depending on your requirements
    }


}
