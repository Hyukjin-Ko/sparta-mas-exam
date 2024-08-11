package com.sparta.msa_exam.order;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("order")
    public String addOrder(@RequestBody CreateOrderRequest request) {
        orderService.createOrder(request);
        return "createOrder!";
    }
    @PutMapping("/order/{orderId}")
    public void addOrderProduct(@PathVariable("orderId") Long orderId, @RequestBody OrderProductRequest request) {
        orderService.addOrderProductToOrder(orderId, request);
    }
    @GetMapping("/order/{orderId}")
    public GetOrderResponse getOrder(@PathVariable("orderId") Long orderId) {
        GetOrderResponse order = orderService.getOrder(orderId);
        return order;
    }
}

