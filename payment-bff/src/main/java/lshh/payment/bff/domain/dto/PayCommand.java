package lshh.payment.bff.domain.dto;

import lshh.payment.bff.domain.entity.Payment;

public record PayCommand(

) {
    public Payment toEntity() {
        // todo
        return null;
    }
}
