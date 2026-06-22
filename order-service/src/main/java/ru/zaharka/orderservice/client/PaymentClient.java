package ru.zaharka.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.zaharka.commonlibraries.http.CreatePaymentRequestDto;
import ru.zaharka.commonlibraries.http.CreatePaymentResponseDto;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/payment")
    CreatePaymentResponseDto makePayment(CreatePaymentRequestDto createPaymentRequestDto);
}
