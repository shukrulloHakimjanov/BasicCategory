package com.example.Category.service;

import com.example.Category.config.JwtService;
import com.example.Category.model.basic.Category;
import com.example.Category.model.basic.CategorySaveDto;
import com.example.Category.model.basic.CategoryGetDto;
import com.example.Category.model.user.Role;
import com.example.Category.model.user.User;
import com.example.Category.model.user.auth.AuthenticationRequest;
import com.example.Category.model.user.auth.AuthenticationResponse;
import com.example.Category.model.user.auth.RegisterRequest;
import com.example.Category.repository.CategoryRepository;
import com.example.Category.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public CategoryGetDto save(CategorySaveDto category) {
        Category category1 = new Category();
        category1.setName(category.getName());
        Category save = categoryRepository.save(category1);
        return new CategoryGetDto(save.getId(), save.getName());
    }

    public void delete(Long id){
        Category employee=categoryRepository.getOne(id);
        categoryRepository.delete(employee);
    }


    public CategoryGetDto update(Long id, CategorySaveDto categoryDto) {
            Category category = categoryRepository.findById(id).orElseThrow();
            category.setName(categoryDto.getName());
            Category save = categoryRepository.save(category);
            return new CategoryGetDto(save.getId(), save.getName());
    }


}
