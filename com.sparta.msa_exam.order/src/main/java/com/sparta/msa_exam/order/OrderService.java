package com.sparta.msa_exam.order;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    @Transactional
    public Long createOrder(CreateOrderRequest request) {
        Order order = orderRepository.save(request.toEntity());
        return order.getOrder_id();
    }


    @CircuitBreaker(name = "addOrderProductToOrder", fallbackMethod = "fallbackAddOrderProductToOrder")
    @Transactional
    public void addOrderProductToOrder(Long orderId, OrderProductRequest req) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NullPointerException(orderId + " 주문 없음"));
        OrderProduct orderProduct = req.toEntity();

        List<GetProductResponse> products = productClient.getProductAll();
        List<Long> productIds = new ArrayList<>();
        products.stream().map(product -> productIds.add(product.getProduct_id())).collect(Collectors.toList());
        Long productId = orderProduct.getProduct_id();
        if (!productIds.contains(productId)) {
            throw new IllegalArgumentException(productId + " 상품 없음");
        }

        orderProduct.addToOrder(order);
    }
    @Transactional
    public GetOrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NullPointerException(orderId + " 주문 없음"));
        return GetOrderResponse.fromEntity(order);
    }

    public void fallbackAddOrderProductToOrder(Throwable t) {
        System.out.println("주문에 상품 추가 실패");
    }

}