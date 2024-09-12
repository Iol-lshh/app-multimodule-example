package lshh.payment.bff.domain.dto;

import lshh.modules.client.payment.service.PaymentState;
import lshh.payment.bff.domain.entity.Payment;
import org.javamoney.moneta.Money;

import java.time.Clock;

public record PayCommand(

) {
    public Payment toEntity(Clock clock) {
        return Payment.builder()
                .sellerId("good-seller")
                .state(PaymentState.PENDING)
                .amount(Money.of(1000, "KRW"))
                .createdAt(clock.instant())
                .build();
    }
}
