package lshh.modules.client.payment.service.dto;

import lshh.modules.client.common.request.RequestParameterException;

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
