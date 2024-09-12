package lshh.core.client.payment.service.dto;

import lshh.core.client.payment.service.PaymentState;
import org.javamoney.moneta.Money;

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
