package ru.zaharka.commonlibraries.dto.payment;

import ru.zaharka.commonlibraries.domain.payment.PaymentMethod;
import ru.zaharka.commonlibraries.domain.payment.PaymentStatus;

import java.math.BigDecimal;

public record PaymentDto(
        long paymentId,
        long orderId,
        PaymentStatus paymentStatus,
        PaymentMethod paymentMethod,
        BigDecimal price
) {
}
