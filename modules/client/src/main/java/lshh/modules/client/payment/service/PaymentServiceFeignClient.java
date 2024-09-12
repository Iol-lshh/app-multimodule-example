package lshh.modules.client.payment.service;

import lshh.modules.client.common.response.Response;
import lshh.modules.client.payment.service.dto.PaymentRequestCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "payment-service", url = "${payment-service.url}")
public interface PaymentServiceFeignClient {

    @PostMapping("/payment/service/request")
    Response request(PaymentRequestCommand command);

    @PostMapping("/payment/service/reject")
    void reject(UUID paymentId);

    @PostMapping("/payment/service/approve")
    void approve(UUID paymentId);
}