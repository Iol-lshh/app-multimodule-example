package lshh.modules.client.payment.service;

import lshh.modules.client.payment.service.dto.PaymentRequestCommand;
import lshh.modules.client.payment.service.dto.PaymentStateView;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payment-service")
public interface PaymentServiceFeignClient {

    PaymentStateView request(PaymentRequestCommand command);
}
