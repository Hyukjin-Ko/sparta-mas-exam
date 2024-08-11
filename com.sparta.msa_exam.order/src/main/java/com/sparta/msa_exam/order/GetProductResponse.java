package com.sparta.msa_exam.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductResponse {
    private Long product_id;
    private String name;
    private Integer supply_price;
}
