package lshh.payment.bff.presentation;

import lombok.RequiredArgsConstructor;
import lshh.modules.client.common.response.Response;
import lshh.payment.bff.domain.PaymentService;
import lshh.payment.bff.domain.dto.PayCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment/platform")
public class PaymentController {
    private final PaymentService service;

    public Response pay(PayCommand command) {
        var result = service.pay(command);
        return Response.ok(result);
    }
}
