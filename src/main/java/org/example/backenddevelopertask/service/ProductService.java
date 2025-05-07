package org.example.backenddevelopertask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backenddevelopertask.dto.product.AddProductRequestDto;
import org.example.backenddevelopertask.dto.product.ProductResponseDto;
import org.example.backenddevelopertask.dto.product.UpdateProductRequestDto;
import org.example.backenddevelopertask.exception.ResourceNotFoundException;
import org.example.backenddevelopertask.mapper.ProductMapper;
import org.example.backenddevelopertask.model.Product;
import org.example.backenddevelopertask.model.ProductImage;
import org.example.backenddevelopertask.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CloudinaryService cloudinaryService;

    public List<ProductResponseDto> getAll() {
        return productMapper.productsToProductsResponseDto(productRepository.findAll());
    }

    public ProductResponseDto getById(Long id) throws ResourceNotFoundException {
        return productMapper.productToProductResponseDto(productRepository.findById(id).orElseThrow(() -> {
            log.error("Can not found product");
            return new ResourceNotFoundException("Can not found product");
        }));
    }

    public ProductResponseDto add(AddProductRequestDto addProductRequestDto) throws IOException {
        Product product = productMapper.addProductResponseDtoToProduct(addProductRequestDto);
        Product save = productRepository.save(product);

        for (MultipartFile image : addProductRequestDto.images()) {
            cloudinaryService.uploadImage(save.getId(), image);
        }

        return productMapper.productToProductResponseDto(save);
    }
    public ProductResponseDto update(Long id, UpdateProductRequestDto updateProductRequestDto) throws ResourceNotFoundException, IOException {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.error("Can not found product");
            return new ResourceNotFoundException("Can not found product");
        });
        if (updateProductRequestDto.images() != null && !updateProductRequestDto.images().isEmpty()) {
            List<ProductImage> productImages = product.getProductImages();
            for (ProductImage productImage:productImages) {
                cloudinaryService.deleteImage(productImage.getImageUrl());
            }
            product.getProductImages().clear();

            for (MultipartFile image: updateProductRequestDto.images()) {
                cloudinaryService.uploadImage(product.getId(), image);
            }
        }
        return productMapper.productToProductResponseDto(productRepository.save(product));
    }

    public void delete(Long id) throws ResourceNotFoundException, IOException {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.error("Can not found product");
            return new ResourceNotFoundException("Can not found product");
        });
        List<ProductImage> productImages = product.getProductImages();
        if (productImages != null) {
            for (ProductImage productImage : productImages) {
                cloudinaryService.deleteImage(productImage.getImageUrl());
            }
        }
        productRepository.delete(product);
    }
}
