package ru.zaharka.commonlibraries.http;

import ru.zaharka.commonlibraries.domain.payment.PaymentMethod;

import java.math.BigDecimal;

public record CreatePaymentRequestDto(
        Long orderId,
        PaymentMethod paymentMethod,
        BigDecimal price
) {
}
