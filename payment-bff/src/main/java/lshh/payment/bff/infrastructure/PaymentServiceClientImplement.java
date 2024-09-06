package lshh.payment.bff.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.modules.client.common.response.Response;
import lshh.modules.client.common.response.ResponseType;
import lshh.modules.client.payment.service.PaymentServiceFeignClient;
import lshh.modules.client.payment.service.dto.PaymentRequestCommand;
import lshh.modules.client.payment.service.dto.PaymentStateView;
import lshh.payment.bff.domain.component.PaymentServiceClient;
import lshh.payment.bff.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class PaymentServiceClientImplement implements PaymentServiceClient {
    private final PaymentServiceFeignClient feignClient;

    @Override
    public PaymentStateView request(String sellerId, Payment payment) {
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
    public void reject(Payment payment) {

    }

    @Override
    public void approve(Payment payment) {

    }
}
