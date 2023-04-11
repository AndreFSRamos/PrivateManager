package br.com.andreramos.privateManager.model.modelDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andreramos.privateManager.model.PaymentMethods;

public interface PaymentMethodsDAO extends JpaRepository<PaymentMethods, Short>{}
