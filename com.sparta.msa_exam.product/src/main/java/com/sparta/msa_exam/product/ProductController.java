package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public String createProducts(@RequestBody CreateProductRequest request){
        productService.createProducts(request);
        return "createProduct!";
    }

    @GetMapping("/products")
    public List<ProductResponseDto> showAllProducts(){
        return productService.showAllProducts();
    }
}