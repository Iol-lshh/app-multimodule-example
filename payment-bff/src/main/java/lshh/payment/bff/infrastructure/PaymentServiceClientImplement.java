package lshh.payment.bff.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.modules.client.common.response.Response;
import lshh.modules.client.common.response.ResponseType;
import lshh.modules.client.payment.service.PaymentServiceFeignClient;
import lshh.modules.client.payment.service.dto.PaymentRequestCommand;
import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.modules.trace.TraceThreadHelper;
import lshh.payment.bff.common.lib.apikey.ApiKeyThreadHelper;
import lshh.payment.bff.domain.component.PaymentServiceClient;
import lshh.payment.bff.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentServiceClientImplement implements PaymentServiceClient {
    private final PaymentServiceFeignClient feignClient;

    @Override
    public PaymentStateView request(Payment payment) {
        String sellerId = ApiKeyThreadHelper.getApiKey().sellerId();
        PaymentRequestCommand command = new PaymentRequestCommand(
                sellerId,
                payment.getAmount().getNumber().longValue(),
                Instant.now().plusSeconds(60)
        );

        Response response = feignClient.request(command);
        if(response.resultCode() != ResponseType.OK.resultCode()){
            throw new RuntimeException("Payment Request Failed");
        }
        return (PaymentStateView) response.data();
    }

    @Override
    public void reject(UUID paymentId) {
        String traceId = TraceThreadHelper.getTraceId();
        log.info("Payment Rejected: [traceId: '{}', paymentId: '{}']", traceId, paymentId);
        feignClient.reject(paymentId);
    }

    @Override
    public void approve(UUID paymentId) {
        String traceId = TraceThreadHelper.getTraceId();
        log.info("Payment Approved: [traceId: '{}', paymentId: '{}']", traceId, paymentId);
        feignClient.approve(paymentId);
    }
}
