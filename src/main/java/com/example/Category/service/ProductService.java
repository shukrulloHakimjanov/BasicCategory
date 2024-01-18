package com.example.Category.service;


import com.example.Category.mapper.CategoryMapper;
import com.example.Category.mapper.ProductMapper;
import com.example.Category.model.basic.Category;
import com.example.Category.model.basic.CategorySaveDto;
import com.example.Category.model.file.FileStorAge;
import com.example.Category.model.file.FileStoreAgeSave;
import com.example.Category.model.product.ProductSaveDto;
import com.example.Category.model.product.ProductEntity;
import com.example.Category.model.product.ProductGetDto;
import com.example.Category.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    private ProductEntity getId(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id not Found")
        );
    }


    public List<ProductEntity> getAll() {
        List<ProductEntity> products = productRepository.findAll();
        return products;
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

    @Transactional
    public ProductGetDto create(ProductSaveDto request) {
        ProductEntity entProduct = productMapper.fromDto(request);
        entProduct.setCategoryId(request.getCategoryId());
        entProduct.setFileId(entProduct.getFileId());
        entProduct = productRepository.save(entProduct);
        return productMapper.toDto(entProduct);
    }




    public void delete(Long id) {
        ProductEntity entProduct = getId(id);
        productRepository.delete(entProduct);
    }

//
@Transactional
public ProductGetDto update(Long id, ProductSaveDto request) {
    ProductEntity entProduct = getId(id);
    productMapper.update(entProduct, request);
    entProduct.setCategoryId(request.getCategoryId());
    entProduct.setFileId(entProduct.getFileId());
    entProduct = productRepository.save(entProduct);
    return productMapper.toDto(entProduct);
}

    private ProductGetDto toDto1(ProductEntity save) {
        return new ProductGetDto(save.getId(), save.getTitle(), save.getDescription(), toDto(save.getCategory()), save.getColor(),toDto2(save.getFileStorAge()));
    }

    private FileStoreAgeSave toDto2(FileStorAge fileStorAge) {
        FileStoreAgeSave dto =new FileStoreAgeSave();
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
