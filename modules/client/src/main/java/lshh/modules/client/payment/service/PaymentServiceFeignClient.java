package lshh.modules.client.payment.service;

import lshh.modules.client.common.response.Response;
import lshh.modules.client.payment.service.dto.PaymentRequestCommand;
import lshh.modules.client.payment.service.dto.PaymentStateView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "payment-service", url = "${payment.service.url}")
public interface PaymentServiceFeignClient {

    @GetMapping("/payment/service/request")
    Response request(PaymentRequestCommand command);
}
