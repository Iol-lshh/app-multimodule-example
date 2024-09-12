package lshh.core.client.payment.service.dto;

import lshh.core.client.common.exception.RequestParameterException;

import java.util.UUID;

public record PaymentRejectCommand(
        UUID paymentId
) {
    public PaymentRejectCommand {
        if (paymentId == null) {
            throw new RequestParameterException("paymentId is required");
        }
    }
}
