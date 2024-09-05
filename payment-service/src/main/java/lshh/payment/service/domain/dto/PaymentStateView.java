package lshh.payment.service.domain.dto;

import org.javamoney.moneta.Money;
import lshh.modules.client.payment.service.PaymentState;

import java.util.UUID;

public record PaymentStateView(
        UUID paymentId,
        String sellerId,
        PaymentState state,
        Long amount
) {
    public PaymentStateView(UUID paymentId, String sellerId, PaymentState state, Money amount) {
        this(paymentId, sellerId, state, amount.getNumber().longValue());
    }
}
