package lshh.payment.bff.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.modules.task.Result;
import lshh.payment.bff.domain.component.PaymentServiceClient;
import lshh.payment.bff.domain.component.PaymentValidator;
import lshh.payment.bff.domain.component.ThirdPartyCardClient;
import lshh.payment.bff.domain.dto.PayCommand;
import lshh.payment.bff.domain.dto.PayResult;
import lshh.payment.bff.domain.entity.Payment;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentServiceClient serviceClient;
    private final ThirdPartyCardClient cardClient;
    private final PaymentValidator validator;

    public Result pay(PayCommand command) {
        validator.validate(command);
        Payment payment = command.toEntity();
        PaymentStateView requestResult = serviceClient.request(payment);
        validator.validate(requestResult);
        payment.setId(requestResult.paymentId());
        PayResult payResult = cardClient.pay(payment);
        validator.validate(payResult);
        serviceClient.approve(payment.getId());
        return Result.ok();
    }
}
