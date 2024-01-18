package com.example.Category.mapper;

import com.example.Category.model.product.ProductEntity;
import com.example.Category.model.product.ProductGetDto;
import com.example.Category.model.product.ProductSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductGetDto toDo(ProductEntity productEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fileStorAge", ignore = true)
    @Mapping(target = "category", ignore = true)
    ProductEntity fromDto(ProductSaveDto productSaveDto);

    @Mapping(target = "fileStorAge", ignore = true)
    @Mapping(target = "category", ignore = true)
    ProductGetDto toDto(ProductEntity entProducts);

    @Mapping(target = "fileStorAge", ignore = true)
    @Mapping(target = "category", ignore = true)
    void update(@MappingTarget ProductEntity productEntity, ProductSaveDto productSaveDto);


}
