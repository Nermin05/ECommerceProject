package org.example.backenddevelopertask.controller;

import lombok.RequiredArgsConstructor;
import org.example.backenddevelopertask.dto.product.AddProductRequestDto;
import org.example.backenddevelopertask.dto.product.ProductResponseDto;
import org.example.backenddevelopertask.dto.product.UpdateProductRequestDto;
import org.example.backenddevelopertask.exception.ResourceNotFoundException;
import org.example.backenddevelopertask.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> add(@ModelAttribute AddProductRequestDto addProductRequestDto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.add(addProductRequestDto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody UpdateProductRequestDto updateProductRequestDto) throws ResourceNotFoundException, IOException {
        return ResponseEntity.ok(productService.update(id, updateProductRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws IOException, ResourceNotFoundException {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
