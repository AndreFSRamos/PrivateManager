package br.com.andreramos.privateManager.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.andreramos.privateManager.exeptionNotFoud.ExeptionNotFound;
import br.com.andreramos.privateManager.model.Categories;
import br.com.andreramos.privateManager.model.modelDAO.CategoriesDAO;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesDAO _categoryDAO;

	public List<Categories> ToList(){
		return _categoryDAO.findAll();
	}
	
	public ResponseEntity<Categories> ToEdit(Short id, Categories newCategory){
		Optional<Categories> oldcategory = _categoryDAO.findById(id);
		
		if(oldcategory.isPresent()) {
			Categories category = oldcategory.get();
			category = newCategory;
			_categoryDAO.save(category);
	        return new ResponseEntity<Categories>(category, HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
	
	public Categories toInsert(Categories newCategory) {
		return	_categoryDAO.save(newCategory);
	}
	
	public ResponseEntity<Object> ToDelete(Short id){
		Optional<Categories> category = _categoryDAO.findById(id);
		
		if(category.isPresent()) {
			_categoryDAO.delete(category.get());
	        return new ResponseEntity<>(HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
}
