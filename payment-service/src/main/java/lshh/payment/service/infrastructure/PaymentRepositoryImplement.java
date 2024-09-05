package lshh.payment.service.infrastructure;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import lshh.payment.service.domain.component.PaymentRepository;
import lshh.payment.service.domain.component.PaymentViewer;
import lshh.payment.service.domain.dto.PaymentListView;
import lshh.payment.service.domain.dto.PaymentStateView;
import lshh.payment.service.domain.entity.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static lshh.payment.service.domain.entity.QPayment.payment;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImplement implements PaymentRepository, PaymentViewer {

    private final PaymentJpaRepository jpaRepository;
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public void save(Payment payment) {
        // id와 상태 log
        log.info("Payment Id : {} - {}", payment.id() == null ? "new request" : payment.id(), payment.state());
        jpaRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(UUID paymentId) {
        return jpaRepository.findById(paymentId);
    }

    @Override
    public PaymentStateView readPayment(UUID paymentId) {
        return jpaQueryFactory.select(Projections.constructor(PaymentStateView.class,
                        payment.id,
                        payment.sellerId,
                        payment.state,
                        payment.paymentProperty.amount
                ))
                .from(payment)
                .where(payment.id.eq(paymentId))
                .fetchOne();
    }

    @Override
    public PaymentListView readBySellerId(String sellerId) {
        List<PaymentStateView> list = jpaQueryFactory.select(Projections.constructor(PaymentStateView.class,
                        payment.id,
                        payment.sellerId,
                        payment.state,
                        payment.paymentProperty.amount
                ))
                .from(payment)
                .where(payment.sellerId.eq(sellerId))
                .fetch();
        return new PaymentListView(list);
    }
}
