package com.sparta.msa_exam.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@NoArgsConstructor
public class GetOrderResponse {
    private Long order_id;
    private List<Long> product_ids = new ArrayList<>();

    public GetOrderResponse(Long order_id, List<Long> product_ids) {
        this.order_id = order_id;
        this.product_ids = product_ids;
    }

    public static GetOrderResponse fromEntity(Order order) {
        List<Long> product_ids = order.getProduct_ids().stream().map(OrderProduct::getProduct_id).collect(Collectors.toList());
        GetOrderResponse result = new GetOrderResponse(order.getOrder_id(), product_ids);
        return result;
    }
}
