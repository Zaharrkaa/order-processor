package ru.zaharka.commonlibraries.dto.order;

import java.util.List;

public record OrderDto(
        Long customerId,
        String address,
        List<OrderItemDto> orderItems
) {
}
