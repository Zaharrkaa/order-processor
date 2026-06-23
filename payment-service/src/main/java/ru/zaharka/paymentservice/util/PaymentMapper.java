package ru.zaharka.paymentservice.util;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.zaharka.commonlibraries.dto.payment.PaymentDto;
import ru.zaharka.commonlibraries.http.CreatePaymentRequestDto;
import ru.zaharka.commonlibraries.http.CreatePaymentResponseDto;
import ru.zaharka.paymentservice.entity.Payment;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    Payment createPaymentRequestDtoToPayment (CreatePaymentRequestDto createPaymentRequestDto);

    CreatePaymentResponseDto paymentToCreatePaymentResponseDto (Payment payment);

    PaymentDto paymentToPaymentDto (Payment payment);
}
