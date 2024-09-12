package lshh.payment.bff.domain.entity;

import lombok.*;
import lshh.modules.client.payment.service.PaymentState;
import org.javamoney.moneta.Money;

import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Payment {
    @Setter
    private UUID id;
    @Setter
    private PaymentState state;
    private String sellerId;
    private Money amount;

    private Instant createdAt;
    @Setter
    private Instant expiredAt;
    @Setter
    private Instant rejectedAt;
}
