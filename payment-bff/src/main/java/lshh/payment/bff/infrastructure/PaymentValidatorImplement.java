package lshh.payment.bff.infrastructure;

import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.payment.bff.domain.component.PaymentValidator;
import lshh.payment.bff.domain.dto.PayCommand;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidatorImplement implements PaymentValidator {
    @Override
    public void validate(PayCommand command) {

    }

    @Override
    public void validate(PaymentStateView requestResult) {

    }
}
