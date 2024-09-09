package lshh.payment.bff.domain.component;

import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.payment.bff.domain.dto.PayCommand;
import lshh.payment.bff.domain.dto.PayResult;

public interface PaymentValidator {
    void validate(PayCommand command);
    void validate(PaymentStateView requestResult);
    void validate(PayResult payResult);
}
