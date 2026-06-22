package ru.zaharka.commonlibraries.http;

import ru.zaharka.commonlibraries.domain.payment.PaymentMethod;
import ru.zaharka.commonlibraries.domain.payment.PaymentStatus;

import java.math.BigDecimal;

public record CreatePaymentResponseDto(
        long orderId,
        long paymentId,
        PaymentStatus paymentStatus,
        PaymentMethod paymentMethod,
        BigDecimal price
) {
}
