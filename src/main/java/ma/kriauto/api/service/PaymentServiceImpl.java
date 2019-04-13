package ma.kriauto.api.service;

import ma.kriauto.api.model.Payment;
import ma.kriauto.api.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
    private PaymentRepository paymentRepository;
	
	@Override
	public Payment fetchPayment() {
		return paymentRepository.fetchPayment();
	}

}
