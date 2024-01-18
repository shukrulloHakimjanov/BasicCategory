package com.example.Category.controller;


import com.example.Category.model.basic.CategorySaveDto;
import com.example.Category.model.basic.CategoryGetDto;
import com.example.Category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
public class CategoryController {
    //TODO log requests
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get Category", description = "Get Category")
    @GetMapping
    public ResponseEntity<List<CategoryGetDto>> getAll(){
        log.info("REST request to get all categories!");
        List<CategoryGetDto> response=categoryService.getAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Post Category", description = "Post Category")
    @PostMapping
    public ResponseEntity<CategoryGetDto> create(@RequestBody CategorySaveDto category) {
        log.info("REST request to Post method  ");
        CategoryGetDto save = categoryService.save(category);
        return ResponseEntity.ok(save);
    }

    @Operation(summary = "PutCategory", description = "PutCategroy")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategorySaveDto categoryDto) {
        log.info("REST request to update id {}",id);
        CategoryGetDto update = categoryService.update(id, categoryDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        log.info("REST request to delete id {}",id);
        categoryService.delete(id);
        return ResponseEntity.ok("Row is delete");
    }

}
