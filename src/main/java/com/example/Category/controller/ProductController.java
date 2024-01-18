package com.example.Category.controller;


import com.example.Category.model.product.ProductEntity;
import com.example.Category.model.product.ProductSaveDto;
import com.example.Category.model.product.ProductGetDto;
import com.example.Category.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductEntity>> getAllCategory() {
        log.info("REST request to get all products");
        List<ProductEntity> pro = productService.getAll();
        return ResponseEntity.ok(pro);
    }

    @GetMapping(path = "/test")
    public String something() {
        return "ok";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductGetDto> create(@RequestBody ProductSaveDto productDto) {
        log.info("REST request to  Post products");
        ProductGetDto save = productService.create(productDto);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductSaveDto productDto) {
        log.info("REST request to update product by id: {}", id);
        ProductGetDto update = productService.update(id ,productDto);
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        log.info("REST request to delete product by id {}",id);
        productService.delete(id);
        return ResponseEntity.ok("Row is delete");
    }
}
