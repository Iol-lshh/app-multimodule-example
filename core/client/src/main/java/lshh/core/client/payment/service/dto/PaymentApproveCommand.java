package lshh.core.client.payment.service.dto;

import lshh.core.client.common.exception.RequestParameterException;

import java.util.UUID;

public record PaymentApproveCommand (
        UUID paymentId
){
    public PaymentApproveCommand {
        if (paymentId == null) {
            throw new RequestParameterException("paymentId is required");
        }
    }
}
