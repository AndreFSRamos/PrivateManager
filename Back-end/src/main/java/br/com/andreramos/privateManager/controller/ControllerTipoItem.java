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
import br.com.andreramos.privateManager.model.Categories;
import br.com.andreramos.privateManager.service.CategoriesService;

@RestController()
@RequestMapping("/tipo_items")
public class ControllerTipoItem {
	
	@Autowired
	private CategoriesService _categoriesService;
	
	@GetMapping
	public List<Categories> Get(){
		return _categoriesService.ToList();
	}
	
	@PostMapping
	public Categories Post(@RequestBody Categories category) {
		try {
			return _categoriesService.toInsert(category);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Não foi possivel incluir um noto tipo de item.", error);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categories>  Put(@PathVariable Short id, @RequestBody Categories newCategory) {
		try {
			return _categoriesService.ToEdit(id, newCategory);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forma de Pagemento não encontrada.", error);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> Delete(@PathVariable Short id){
		try {
			return _categoriesService.ToDelete(id);
		}catch (ExeptionNotFound error) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O id("+id+") não foi encontrado.", error);
		}
	}

}
