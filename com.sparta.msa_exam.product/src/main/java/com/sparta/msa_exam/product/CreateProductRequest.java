package com.sparta.msa_exam.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateProductRequest {
//    private final Long product_id;
    private final String name;
    private final Integer supply_price;

}
