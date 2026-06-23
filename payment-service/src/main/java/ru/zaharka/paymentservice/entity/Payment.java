package ru.zaharka.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.zaharka.commonlibraries.domain.payment.PaymentMethod;
import ru.zaharka.commonlibraries.domain.payment.PaymentStatus;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "payments")
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "order_id")
    private Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "price")
    private BigDecimal price;

    public Payment(PaymentMethod paymentMethod, BigDecimal price, Long orderId) {
        this.paymentMethod = paymentMethod;
        this.price = price;
        this.orderId = orderId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
