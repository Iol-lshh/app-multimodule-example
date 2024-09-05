package lshh.payment.bff.domain.component;

import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.payment.bff.domain.entity.Payment;

public interface PaymentServiceClient {
    PaymentStateView request(String sellerId, Payment payment);

    void reject(Payment payment);

    void approve(Payment payment);
}
