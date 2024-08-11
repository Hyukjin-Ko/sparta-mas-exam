package com.sparta.msa_exam.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long order_product_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    private Long product_id;

    public OrderProduct(Long product_id) {
        this.product_id = product_id;
    }

    public void addToOrder(Order order) {
        this.order = order;
        order.addOrderProduct(this);
    }
}
