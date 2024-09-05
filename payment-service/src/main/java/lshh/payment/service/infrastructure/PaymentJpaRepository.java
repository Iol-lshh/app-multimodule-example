package lshh.payment.service.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import lshh.payment.service.domain.entity.Payment;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<Payment, UUID> {

}
