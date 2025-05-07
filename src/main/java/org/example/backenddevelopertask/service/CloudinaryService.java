package org.example.backenddevelopertask.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backenddevelopertask.model.Product;
import org.example.backenddevelopertask.model.ProductImage;
import org.example.backenddevelopertask.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final ProductRepository productRepository;

    public String uploadImage(Long cloudinaryId, MultipartFile file) throws IOException {
        Product product = productRepository.findById(cloudinaryId).orElseThrow(() -> {
            log.error("Product can not found");
            return new NoSuchElementException("Product can not found");
        });
        Map<?, ?> uploaded = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = uploaded.get("secure_url").toString();
        ProductImage productImage = new ProductImage();
        productImage.setImageUrl(imageUrl);
        productImage.setProduct(product);
        List<ProductImage> productImages = product.getProductImages();
        if (productImages == null) {
            productImages = new ArrayList<>();
        }
        productImages.add(productImage);
        product.setProductImages(productImages);

        productRepository.save(product);
        return imageUrl;
    }

    public void deleteImage(String imageUrl) throws IOException {
        String publicId = extractImageUrl(imageUrl);
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    private String extractImageUrl(String imageUrl) {
        String[] split = imageUrl.split("/");
        String file = split[split.length - 1];
        return file.split("\\.")[0];
    }
}
