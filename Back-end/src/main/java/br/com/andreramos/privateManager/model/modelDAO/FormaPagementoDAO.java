package br.com.andreramos.privateManager.model.modelDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andreramos.privateManager.model.FormaPagamento;

public interface FormaPagementoDAO extends JpaRepository<FormaPagamento, Short>{}
