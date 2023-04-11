package br.com.andreramos.privateManager.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.andreramos.privateManager.exeptionNotFoud.ExeptionNotFound;
import br.com.andreramos.privateManager.model.PaymentMethods;
import br.com.andreramos.privateManager.model.modelDAO.PaymentMethodsDAO;

@Service
public class PaymentMethodsService {
	
	@Autowired
	private PaymentMethodsDAO _paymentMethodsDAO;
	
	
	
	public List<PaymentMethods> ToList(){
		return _paymentMethodsDAO.findAll();
	}
	
	
	
	public ResponseEntity<PaymentMethods> ToEdit(Short id, PaymentMethods newPaymentMethods){
		Optional<PaymentMethods> oldPaymentMethods = _paymentMethodsDAO.findById(id);
		
		if(oldPaymentMethods.isPresent()) {
			PaymentMethods paymentMethods = oldPaymentMethods.get();
			paymentMethods = newPaymentMethods;
			_paymentMethodsDAO.save(paymentMethods);
	        return new ResponseEntity<PaymentMethods>(paymentMethods, HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
	
	
	
	
	public PaymentMethods toInsert(PaymentMethods paymentMethods) {
		return	_paymentMethodsDAO.save(paymentMethods);
	}
	
	
	
	
	public ResponseEntity<Object> ToDelete(Short id){
		Optional<PaymentMethods> paymentMethods = _paymentMethodsDAO.findById(id);
		
		if(paymentMethods.isPresent()) {
			_paymentMethodsDAO.delete(paymentMethods.get());
	        return new ResponseEntity<>(HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
}