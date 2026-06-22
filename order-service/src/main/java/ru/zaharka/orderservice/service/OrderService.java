package ru.zaharka.orderservice.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zaharka.commonlibraries.domain.order.OrderStatus;
import ru.zaharka.commonlibraries.domain.payment.PaymentMethod;
import ru.zaharka.commonlibraries.domain.payment.PaymentStatus;
import ru.zaharka.commonlibraries.dto.order.OrderDto;
import ru.zaharka.commonlibraries.dto.order.OrderItemDto;
import ru.zaharka.commonlibraries.dto.order.OrderResponseDto;
import ru.zaharka.commonlibraries.http.CreatePaymentRequestDto;
import ru.zaharka.commonlibraries.http.CreatePaymentResponseDto;
import ru.zaharka.orderservice.client.PaymentClient;
import ru.zaharka.orderservice.entity.Order;
import ru.zaharka.orderservice.entity.OrderItem;
import ru.zaharka.orderservice.repository.OrderRepository;
import ru.zaharka.orderservice.util.OrderMapper;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PaymentClient paymentClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.paymentClient = paymentClient;
    }

    @Transactional
    public OrderResponseDto createOrder(OrderDto orderDto) {
        System.out.println("начинаем создавать заказ");
        List<OrderItem> orderItems = orderDto.orderItems().stream().map(orderMapper::orderItemDtoToOrderItem).toList();
        Order order = orderMapper.orderDtoToOrder(orderDto);
        order.setOrderItems(orderItems);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }
        order.setOrderStatus(OrderStatus.PAYMENT_PENDING);
        order.setPrice(calculatePrice(order.getOrderItems()));
        order = orderRepository.save(order);
        return orderMapper.orderToOrderResponseDto(order);
    }

    private BigDecimal calculatePrice(List<OrderItem> orderItems) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            totalPrice = totalPrice.add(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
        }
        return totalPrice;
    }

    @Transactional
    public OrderResponseDto payOrder (long orderId, PaymentMethod paymentMethod) {
        Optional<Order> orderOptional = orderRepository.findByOrderId(orderId);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("Order not found");
        }
        Order order = orderOptional.get();
        if(!order.getOrderStatus().equals(OrderStatus.PAYMENT_PENDING)){
            throw new RuntimeException("Order status is not PAYMENT_PENDING");
        }
        CreatePaymentRequestDto createPaymentRequestDto = new CreatePaymentRequestDto(order.getOrderId(), paymentMethod, order.getPrice());
        CreatePaymentResponseDto createPaymentResponseDto = paymentClient.makePayment(createPaymentRequestDto);
        if(createPaymentResponseDto.paymentStatus().equals(PaymentStatus.SUCCEEDED)){
            order.setOrderStatus(OrderStatus.PAID);
        }
        else{
            order.setOrderStatus(OrderStatus.PAYMENT_FAILED);
        }
        order = orderRepository.save(order);
        return orderMapper.orderToOrderResponseDto(order);
    }

}
