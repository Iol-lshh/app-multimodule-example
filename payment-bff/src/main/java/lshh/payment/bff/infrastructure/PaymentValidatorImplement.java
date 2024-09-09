package lshh.payment.bff.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.modules.trace.TraceThreadHelper;
import lshh.payment.bff.domain.component.PaymentServiceClient;
import lshh.payment.bff.domain.component.PaymentValidator;
import lshh.payment.bff.domain.dto.PayCommand;
import lshh.payment.bff.domain.dto.PayResult;
import lshh.payment.bff.domain.exception.CardClientNotOkException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentValidatorImplement implements PaymentValidator {
    private final PaymentServiceClient serviceClient;

    @Override
    public void validate(PayCommand command) {

    }

    @Override
    public void validate(PaymentStateView requestResult) {

    }

    @Override
    public void validate(PayResult result) {
        String traceId = TraceThreadHelper.getTraceId();
        UUID paymentId = result.getPaymentId();

        if(!result.isOk()){
            serviceClient.reject(paymentId);
            throw new CardClientNotOkException("Card Client Not Ok: [traceId: '" + traceId + "', paymentId: '" + paymentId + "']");
        }
    }
}
