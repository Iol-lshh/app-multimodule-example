package lshh.payment.service.domain.dto;

import lshh.modules.client.common.request.RequestParameterException;
import org.javamoney.moneta.Money;
import lshh.payment.service.domain.entity.Payment;
import lshh.payment.service.domain.vo.PaymentProperty;
import lshh.modules.client.payment.service.PaymentState;

import java.time.Clock;
import java.time.Instant;


public record PaymentRequestCommand(
        // buyer 정보
        String sellerId,
        Long amount,
        Instant expiredAt

){
    public PaymentRequestCommand {
        Money _amount = Money.of(amount, "KRW");
        if (_amount.isNegativeOrZero()) {
            throw new RequestParameterException("Amount must be positive");
        }
    }

    public Payment toEntity(Clock clock) {
        Money amount = Money.of(this.amount, "KRW");

        return Payment.builder()
                .paymentProperty(PaymentProperty.builder()
                        .amount(amount)
                        .build()
                )
                .sellerId(sellerId)
                .state(PaymentState.PENDING)
                .createdAt(clock.instant())
                .expiredAt(expiredAt)
                .build();
    }
}
