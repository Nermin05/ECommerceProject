package org.example.backenddevelopertask.dto.product;

import org.example.backenddevelopertask.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDto(String productName,BigDecimal price,
                                 List<String> imageUrls){
}
