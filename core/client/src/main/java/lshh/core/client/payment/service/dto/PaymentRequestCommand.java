package lshh.core.client.payment.service.dto;

import lshh.core.client.common.exception.RequestParameterException;
import org.javamoney.moneta.Money;

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
}
