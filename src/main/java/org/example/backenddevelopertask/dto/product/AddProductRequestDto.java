package org.example.backenddevelopertask.dto.product;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record AddProductRequestDto(String productName, BigDecimal price,
                                   List<MultipartFile> images) {
}
