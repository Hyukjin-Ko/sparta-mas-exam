package com.sparta.msa_exam.order;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
public class CreateOrderRequest {
    @NotNull
    private String name;
    private List<OrderProductRequest> productIds;

    public Order toEntity() {
        Order order = new Order(this.name);
        productIds.stream().forEach(productId -> {
            OrderProduct orderProduct = productId.toEntity();
            orderProduct.addToOrder(order);
        });
        return order;
    }


}
