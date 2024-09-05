package lshh.payment.bff.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lshh.modules.client.payment.service.PaymentState;
import org.javamoney.moneta.Money;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Payment {
    @Setter
    private UUID id;
    private PaymentState state;
    private String sellerId;
    private Money amount;

    private Instant createdAt;
    private Instant expiredAt;
    private Instant rejectedAt;
}
