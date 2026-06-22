package ru.zaharka.commonlibraries.dto.order;

import java.math.BigDecimal;

public record OrderItemDto(
        Integer quantity,
        Long itemId,
        BigDecimal price
) {
}
