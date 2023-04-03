package br.com.andreramos.privateManager.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.andreramos.privateManager.exeptionNotFoud.ExeptionNotFound;
import br.com.andreramos.privateManager.model.FormaPagamento;
import br.com.andreramos.privateManager.model.modelDAO.FormaPagementoDAO;

@Service
public class FormaPagamentoService {
	
	@Autowired
	private FormaPagementoDAO _formapagamentoDAO;
	
	
	
	public List<FormaPagamento> ToList(){
		return _formapagamentoDAO.findAll();
	}
	
	
	
	public ResponseEntity<FormaPagamento> ToEdit(Short id, FormaPagamento newFormaPagamento){
		Optional<FormaPagamento> oldformapagmaneto = _formapagamentoDAO.findById(id);
		
		if(oldformapagmaneto.isPresent()) {
			FormaPagamento formaPagamento = oldformapagmaneto.get();
			formaPagamento = newFormaPagamento;
			_formapagamentoDAO.save(formaPagamento);
	        return new ResponseEntity<FormaPagamento>(formaPagamento, HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
	
	
	
	
	public FormaPagamento toInsert(FormaPagamento formaPagamento) {
		return	_formapagamentoDAO.save(formaPagamento);
	}
	
	
	
	
	public ResponseEntity<Object> ToDelete(Short id){
		Optional<FormaPagamento> formapagmaneto = _formapagamentoDAO.findById(id);
		
		if(formapagmaneto.isPresent()) {
			_formapagamentoDAO.delete(formapagmaneto.get());
	        return new ResponseEntity<>(HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
}