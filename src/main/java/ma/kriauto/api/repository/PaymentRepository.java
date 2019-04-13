package ma.kriauto.api.repository;

import ma.kriauto.api.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	@Query("SELECT p FROM Payment p")
	public Payment fetchPayment();

}
