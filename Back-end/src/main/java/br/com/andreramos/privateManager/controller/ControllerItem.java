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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.andreramos.privateManager.exeptionNotFoud.ExeptionNotFound;
import br.com.andreramos.privateManager.model.Item;
import br.com.andreramos.privateManager.service.ItemService;

@RestController()
@RequestMapping("/items")
public class ControllerItem {
	@Autowired
	private ItemService _itemService;
	
	@GetMapping
	public List<Item> Get() {
		return _itemService.ToList();
	}
	
	@PostMapping
	public Item Post(@RequestBody Item item) {
		try {
			return _itemService.toInsert(item);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Não foi possivel incluir um novo item.", error);
		}			
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Item>  Put(@PathVariable Short id, @RequestBody Item item) {
		try {
			return _itemService.ToEdit(id, item);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item não encontrado", error);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> Delete(@PathVariable Short id){
		try {
			return _itemService.ToDelete(id);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O id("+id+") não foi encontrado.", error);
		}
	}
	@GetMapping("/total/categotry")
	public int getQtdTotalPerCategory(@RequestParam(required = true) String findReference, @RequestParam(required = true) String findDate){
		return _itemService.getQtdTotalPerCategory( findReference, findDate);
	}

	@GetMapping("/total/paymmentMethods")
	public int getQtdTotalPerPaymentMethods(@RequestParam(required = true) String findReference, @RequestParam(required = true) String findDate){
		return _itemService.getQtdTotalPerPaymentMethods( findReference, findDate);
	}
}
