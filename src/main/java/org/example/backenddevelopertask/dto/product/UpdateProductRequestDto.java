package org.example.backenddevelopertask.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record UpdateProductRequestDto(@NotBlank String productName,
                                      @NotNull BigDecimal price,
                                      List<MultipartFile> images ){
}
