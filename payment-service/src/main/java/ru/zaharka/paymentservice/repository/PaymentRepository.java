package ru.zaharka.paymentservice.repository;

import org.springframework.stereotype.Repository;
import ru.zaharka.paymentservice.entity.Payment;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private List<Payment> payments;

    public PaymentRepository() {
        payments = new ArrayList<>();
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }
}
