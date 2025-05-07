package org.example.backenddevelopertask.mapper;

import org.example.backenddevelopertask.dto.product.AddProductRequestDto;
import org.example.backenddevelopertask.dto.product.ProductResponseDto;
import org.example.backenddevelopertask.dto.product.UpdateProductRequestDto;
import org.example.backenddevelopertask.model.Product;
import org.example.backenddevelopertask.model.ProductImage;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "imageUrls", expression = "java(imageUrls(product.getProductImages()))")
    ProductResponseDto productToProductResponseDto(Product product);

    List<ProductResponseDto> productsToProductsResponseDto(List<Product> products);

    @Mapping(target = "productImages", source = "images")
    Product addProductResponseDtoToProduct(AddProductRequestDto addProductRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductDtoToProduct(UpdateProductRequestDto updateProductRequestDto, @MappingTarget Product product);

    @Named("imageUrls")
    default List<String> imageUrls(List<ProductImage> productImages) {
        if (productImages == null) return List.of();
        return productImages.stream().map(ProductImage::getImageUrl).toList();
    }
}
