package lshh.payment.service.domain;

import lombok.RequiredArgsConstructor;
import lshh.modules.clock.ClockManager;
import lshh.payment.service.domain.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lshh.payment.service.domain.component.PaymentRepository;
import lshh.payment.service.domain.component.PaymentValidator;
import lshh.payment.service.domain.component.PaymentViewer;
import lshh.payment.service.domain.entity.Payment;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentValidator validator;
    private final PaymentRepository repository;
    private final PaymentViewer viewer;
    private final ClockManager clockManager;

    @Transactional
    public PaymentStateView request(PaymentRequestCommand command) {
        validator.validate(command);
        Payment payment = command.toEntity(clockManager.getClock());
        repository.save(payment);
        return viewer.readPayment(payment.id());
    }

    @Transactional
    public PaymentStateView approve(PaymentApproveCommand command) {
        validator.validate(command);
        Payment payment = repository.findById(command.paymentId()).get();
        payment.approve(clockManager.getClock());
        repository.save(payment);
        return viewer.readPayment(payment.id());
    }

    @Transactional
    public PaymentStateView cancel(PaymentCancelCommand command) {
        validator.validate(command);
        Payment payment = repository.findById(command.paymentId()).get();
        payment.cancel(clockManager.getClock());
        repository.save(payment);
        return viewer.readPayment(payment.id());
    }

    @Transactional
    public PaymentStateView reject(PaymentRejectCommand command) {
        validator.validate(command);
        Payment payment = repository.findById(command.paymentId()).get();
        payment.reject(clockManager.getClock());
        repository.save(payment);
        return viewer.readPayment(payment.id());
    }

    public PaymentListView readBySellerId(String sellerId) {
        return viewer.readBySellerId(sellerId);
    }
}
