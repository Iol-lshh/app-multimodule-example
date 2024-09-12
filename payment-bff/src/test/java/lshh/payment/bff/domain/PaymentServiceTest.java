package lshh.payment.bff.domain;

import lshh.modules.apikey.ApiKey;
import lshh.modules.apikey.ApiKeyThreadHelper;
import lshh.modules.apikey.ApiType;
import lshh.modules.clock.ClockManager;
import lshh.modules.task.Result;
import lshh.payment.bff.PaymentBffApplication;
import lshh.payment.bff.TestPaymentBffApplication;
import lshh.payment.bff.domain.component.PaymentClient;
import lshh.payment.bff.domain.component.PaymentValidator;
import lshh.payment.bff.domain.component.ThirdPartyCardClient;
import lshh.payment.bff.domain.dto.PayCommand;
import lshh.payment.bff.domain.dto.PayResult;
import lshh.payment.bff.domain.entity.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = TestPaymentBffApplication.class)
public class PaymentServiceTest {

    private PaymentService paymentService;

    @Autowired
    private PaymentClient serviceClient;

    @Mock
    private ThirdPartyCardClient cardClient;

    @Mock
    private PaymentValidator validator;

    private ClockManager clockManager = new ClockManager() {
        @Override
        public Clock getClock() {
            return Clock.system(ZoneId.of("Asia/Seoul"));
        }

        @Override
        public ChronoLocalDateTime<?> getTodayStart() {
            return Instant.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDate().atStartOfDay();
        }
    };

    @BeforeEach
    public void setUp() {
        paymentService = new PaymentService(serviceClient, cardClient, validator, clockManager);
    }

    @Nested
    class PayCommandTests {
        @Test
        public void 성공() {
            ApiKeyThreadHelper.setApiKey(new ApiKey("test", "good-seller", ApiType.TEST));
            PayCommand command = new PayCommand();
            doNothing().when(validator).validate(any(PayCommand.class));
            PayResult payResult = new PayResult(UUID.randomUUID(), true);
            when(cardClient.pay(any(Payment.class))).thenReturn(payResult);

            Result result = paymentService.pay(command);
            assert (result.isOk());

            verify(serviceClient).request(any());
            verify(cardClient).pay(any());
        }

//        @Test
//        public void testPayFailure() {
//            PayCommand command = new PayCommand(); // Initialize with proper values
//            Payment payment = command.toEntity();
//            PaymentStateView paymentStateView = new PaymentStateView(); // Initialize with proper values
//
//            when(serviceClient.request(payment)).thenReturn(paymentStateView);
//            when(cardClient.pay(payment)).thenReturn(Result.from(new RuntimeException("Card payment failure")));
//
//            try {
//                paymentService.pay(command);
//            } catch (RuntimeException e) {
//                assert (e.getMessage().equals("Card payment failure"));
//            }
//
//            verify(serviceClient).request(any());
//            verify(cardClient).pay(any());
//            verify(validator, times(2)).validate(any());
//        }
    }

}