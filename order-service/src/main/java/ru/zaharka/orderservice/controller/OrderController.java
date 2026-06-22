package ru.zaharka.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.zaharka.commonlibraries.domain.payment.PaymentMethod;
import ru.zaharka.commonlibraries.dto.order.OrderDto;
import ru.zaharka.commonlibraries.dto.order.OrderResponseDto;
import ru.zaharka.orderservice.service.OrderService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public OrderResponseDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }


    @PostMapping("/{id}/pay")
    public OrderResponseDto processOrder(@PathVariable("id") long orderId, @RequestBody PaymentMethod paymentMethod) {
        System.out.println("Запрос от инстанса: " + instanceId);
        return orderService.payOrder(orderId, paymentMethod);
    }

}
