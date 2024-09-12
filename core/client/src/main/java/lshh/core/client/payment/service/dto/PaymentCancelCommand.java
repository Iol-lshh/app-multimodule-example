package lshh.core.client.payment.service.dto;

import lshh.core.client.common.exception.RequestParameterException;

import java.util.UUID;

public record PaymentCancelCommand(
        UUID paymentId
) {
    public PaymentCancelCommand {
        if (paymentId == null) {
            throw new RequestParameterException("paymentId is required");
        }
    }
}
