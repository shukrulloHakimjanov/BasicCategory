package com.example.Category.mapper;

import com.example.Category.model.basic.Category;
import com.example.Category.model.basic.CategoryGetDto;
import com.example.Category.model.basic.CategorySaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryGetDto toDto(Category category);

    @Mapping(target = "id",ignore = true)
    Category fromDto(CategorySaveDto categoryDto);



    @Mapping(target = "id",ignore = true)
    void update(@MappingTarget Category category, CategorySaveDto categoryDto);

    List<CategoryGetDto> toDto(List<Category> categories);
}
