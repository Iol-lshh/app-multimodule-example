package lshh.payment.bff.domain.dto;

import lshh.payment.bff.common.lib.apikey.ApiKey;
import lshh.payment.bff.domain.entity.Payment;

public record PayCommand(

) {
    public Payment toEntity(ApiKey apiKey) {
        // todo
        return null;
    }
}
