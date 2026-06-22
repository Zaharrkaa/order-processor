package ru.zaharka.commonlibraries.dto.order;

import ru.zaharka.commonlibraries.domain.order.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDto(
        Long orderId,
        Long customerId,
        String address,
        List<OrderItemDto> orderItems,
        OrderStatus orderStatus,
        BigDecimal price
) {
}
