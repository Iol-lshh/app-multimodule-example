package lshh.payment.bff.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.modules.task.Result;
import lshh.modules.trace.TraceThreadHelper;
import lshh.payment.bff.common.lib.apikey.ApiKey;
import lshh.payment.bff.common.lib.apikey.ApiKeyThreadHelper;
import lshh.payment.bff.domain.component.PaymentServiceClient;
import lshh.payment.bff.domain.component.PaymentValidator;
import lshh.payment.bff.domain.component.ThirdPartyCardClient;
import lshh.payment.bff.domain.dto.PayCommand;
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
        String traceId = TraceThreadHelper.getTraceId();
        ApiKey apiKey = ApiKeyThreadHelper.getApiKey();
        validator.validate(command);
        Payment payment = command.toEntity(apiKey);
        PaymentStateView requestResult = serviceClient.request(apiKey.sellerId(), payment);
        validator.validate(requestResult);
        payment.setId(requestResult.paymentId());
        Result result = cardClient.pay(payment);
        if(!result.isOk()){
            log.warn("Payment rejected: [traceId: '{}', paymentId: '{}']", traceId, payment.getId());
            serviceClient.reject(payment);
            return result;
        }
        log.info("Payment Approved: [traceId: '{}', paymentId: '{}']", traceId, payment.getId());
        serviceClient.approve(payment);
        return Result.ok();
    }
}
