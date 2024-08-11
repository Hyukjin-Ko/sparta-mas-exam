package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProducts(CreateProductRequest request){
        Product product = new Product(request.getName(), request.getSupply_price());
        productRepository.save(product);
    }

    public List<ProductResponseDto> showAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponseDto(
                        product.getProduct_id(),
                        product.getName(),
                        product.getSupply_price()))
                .collect(Collectors.toList());
    }
}
