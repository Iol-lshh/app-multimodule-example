package lshh.payment.bff.domain.component;

import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.payment.bff.domain.entity.Payment;

import java.util.UUID;

public interface PaymentServiceClient {
    PaymentStateView request(Payment payment);

    void reject(UUID payment);

    void approve(UUID paymentId);
}
