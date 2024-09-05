package lshh.payment.service.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lshh.payment.service.domain.vo.PaymentProperty;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PaymentApproved {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @Embedded
    private PaymentProperty paymentProperty;

    private Instant approvedAt;
}
