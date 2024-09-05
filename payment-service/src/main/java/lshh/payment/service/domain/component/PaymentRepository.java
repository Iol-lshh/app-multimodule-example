package lshh.payment.service.domain.component;


import lshh.payment.service.domain.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    void save(Payment payment);

    Optional<Payment> findById(UUID paymentId);
}
