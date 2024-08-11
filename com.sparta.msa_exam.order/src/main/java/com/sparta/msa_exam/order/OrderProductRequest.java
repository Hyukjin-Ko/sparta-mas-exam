package com.sparta.msa_exam.order;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;
@Getter
public class OrderProductRequest {
    @NotNull
    private Long product_id;

    public OrderProduct toEntity() {
        return new OrderProduct(this.product_id);
    }
}
