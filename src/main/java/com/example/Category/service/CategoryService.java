package com.example.Category.service;

import com.example.Category.mapper.CategoryMapper;
import com.example.Category.model.basic.Category;
import com.example.Category.model.basic.CategorySaveDto;
import com.example.Category.model.basic.CategoryGetDto;
import com.example.Category.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryGetDto> getAll() {
        List<Category>categories=categoryRepository.findAll();
        return categoryMapper.toDto(categories);
    }
    @Transactional(readOnly = true)
    public CategoryGetDto save(CategorySaveDto category) {
        Category category1 =categoryMapper.fromDto(category);
        category1 = categoryRepository.save(category1);
        return categoryMapper.toDto(category1);
    }

    @Transactional
    public void delete(Long id){
        Category entCategory=categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Category is not found!"));
        categoryRepository.delete(entCategory);
    }


    @Transactional
    public CategoryGetDto update(Long id, CategorySaveDto request){
        Category entCategory=categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category is not found!"));
        categoryMapper.update(entCategory, request);
        entCategory=categoryRepository.save(entCategory);
        return categoryMapper.toDto(entCategory);
    }


}
