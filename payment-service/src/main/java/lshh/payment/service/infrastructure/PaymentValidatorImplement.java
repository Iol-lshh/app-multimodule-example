package lshh.payment.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import lshh.payment.service.common.lib.jpa.PersistentNotFoundException;
import lshh.payment.service.domain.component.PaymentValidator;
import lshh.payment.service.domain.dto.PaymentApproveCommand;
import lshh.payment.service.domain.dto.PaymentCancelCommand;
import lshh.payment.service.domain.dto.PaymentRejectCommand;
import lshh.payment.service.domain.dto.PaymentRequestCommand;
import lshh.payment.service.domain.entity.Payment;
import lshh.payment.service.domain.exception.PaymentStateException;

@RequiredArgsConstructor
@Repository
public class PaymentValidatorImplement implements PaymentValidator {

    private final PaymentJpaRepository jpaRepository;

    @Override
    public void validate(PaymentRequestCommand command) {
    }

    @Override
    public void validate(PaymentApproveCommand command) {
        Payment payment = jpaRepository.findById(command.paymentId())
                .orElseThrow(()-> new PersistentNotFoundException("Payment Not Found"));
        if(!payment.isPending()){
            throw new PaymentStateException("Payment Not Pending");
        }
    }

    @Override
    public void validate(PaymentCancelCommand command) {
        Payment payment = jpaRepository.findById(command.paymentId())
                .orElseThrow(()-> new PersistentNotFoundException("Payment Not Found"));
        if(!payment.isApproved()){
            throw new PaymentStateException("Payment Not Approved");
        }
//        if(payment.isFinished()){
//            throw new PaymentStateException("Payment Already Finished");
//        }
    }

    @Override
    public void validate(PaymentRejectCommand command) {
        Payment payment = jpaRepository.findById(command.paymentId())
                .orElseThrow(()-> new PersistentNotFoundException("Payment Not Found"));
        if(!payment.isPending()){
            throw new PaymentStateException("Payment Not Pending");
        }
    }
}
