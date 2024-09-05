package lshh.payment.service.domain.component;

import lshh.payment.service.domain.dto.PaymentApproveCommand;
import lshh.payment.service.domain.dto.PaymentCancelCommand;
import lshh.payment.service.domain.dto.PaymentRejectCommand;
import lshh.payment.service.domain.dto.PaymentRequestCommand;

public interface PaymentValidator {
    void validate(PaymentRequestCommand command);
    void validate(PaymentApproveCommand command);
    void validate(PaymentCancelCommand command);
    void validate(PaymentRejectCommand command);
}
