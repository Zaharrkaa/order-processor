package ru.zaharka.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.zaharka.commonlibraries.dto.payment.PaymentDto;
import ru.zaharka.commonlibraries.http.CreatePaymentRequestDto;
import ru.zaharka.commonlibraries.http.CreatePaymentResponseDto;
import ru.zaharka.paymentservice.service.PaymentService;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping()
    public List<PaymentDto> getPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping()
    public CreatePaymentResponseDto createPayment(@RequestBody CreatePaymentRequestDto createPaymentRequestDto) {
        System.out.println("Ответ от инстанса: " + instanceId);
        return paymentService.createPayment(createPaymentRequestDto);
    }
}
