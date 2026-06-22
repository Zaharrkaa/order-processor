package ru.zaharka.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zaharka.commonlibraries.domain.payment.PaymentMethod;
import ru.zaharka.commonlibraries.domain.payment.PaymentStatus;
import ru.zaharka.commonlibraries.dto.payment.PaymentDto;
import ru.zaharka.commonlibraries.http.CreatePaymentRequestDto;
import ru.zaharka.commonlibraries.http.CreatePaymentResponseDto;
import ru.zaharka.paymentservice.entity.Payment;
import ru.zaharka.paymentservice.repository.PaymentRepository;
import ru.zaharka.paymentservice.util.PaymentMapper;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public CreatePaymentResponseDto createPayment(CreatePaymentRequestDto createPaymentRequestDto) {

        Payment payment = paymentMapper.createPaymentRequestDtoToPayment(createPaymentRequestDto);
        payment.setPaymentId(1);
        if(payment.getPaymentMethod().equals(PaymentMethod.CARD)){
            payment.setPaymentStatus(PaymentStatus.SUCCEEDED);
        }
        else{
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }
        paymentRepository.addPayment(payment);
        return paymentMapper.paymentToCreatePaymentResponseDto(payment);
    }

    public List<PaymentDto> getAllPayments() {
        return paymentRepository.getPayments().stream().map(paymentMapper::paymentToPaymentDto).toList();
    }


}
