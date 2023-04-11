package br.com.andreramos.privateManager.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.andreramos.privateManager.exeptionNotFoud.ExeptionNotFound;
import br.com.andreramos.privateManager.model.PaymentMethods;
import br.com.andreramos.privateManager.service.PaymentMethodsService;

@RestController()
@RequestMapping("/Formas_de_pagamento")
public class ControllerFormaPagamento {

	@Autowired
	private PaymentMethodsService _paymentMethodsService;
	
	@GetMapping
	public List<PaymentMethods> Get(){
		return _paymentMethodsService.ToList();
	}
	
	@PostMapping
	public PaymentMethods Post(@RequestBody PaymentMethods paymentMethods) {
		try {
			return _paymentMethodsService.toInsert(paymentMethods);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Não foi possivel incluir uma nova forma de pagamento.", error);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PaymentMethods>  Put(@PathVariable Short id, @RequestBody PaymentMethods newPaymentMethods) {
		try {
			return _paymentMethodsService.ToEdit(id, newPaymentMethods);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forma de Pagemento não encontrada.", error);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> Delete(@PathVariable Short id){
		try {
			return _paymentMethodsService.ToDelete(id);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O id("+id+") não foi encontrado.", error);
		}
	}
}
