package lshh.payment.bff.domain.dto;

import java.util.UUID;

public record PayResult(
    UUID paymentId,
    boolean ok
) {
    public boolean isOk() {
        return ok;
    }

    public UUID getPaymentId() {
        return paymentId;
    }
}
