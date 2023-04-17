package br.com.andreramos.privateManager.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.andreramos.privateManager.exeptionNotFoud.ExeptionNotFound;
import br.com.andreramos.privateManager.model.Item;
import br.com.andreramos.privateManager.model.modelDAO.ItemDAO;

@Service
public class ItemService {

	@Autowired
	private ItemDAO _itemdao;

	public List<Item> ToList(){
		return _itemdao.findAll();
	}
	
	public ResponseEntity<Item> ToEdit(Short id, Item newItem){
		Optional<Item> oldItem = _itemdao.findById(id);
		
		if(oldItem.isPresent()) {
			Item item = oldItem.get();
			item = newItem;
			_itemdao.save(item);
	        return new ResponseEntity<Item>(item, HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
	
	public Item toInsert(Item newItem) {
		return	_itemdao.save(newItem);
	}
	
	public ResponseEntity<Object> ToDelete(Short id){
		Optional<Item> item = _itemdao.findById(id);
		
		if(item.isPresent()) {
			_itemdao.delete(item.get());
	        return new ResponseEntity<>(HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}

	public int getQtdTotalPerCategory(String findReference, String findDate){
		return _itemdao.findQtdTotalPerCategory(findReference, findDate);
	}

	public int getQtdTotalPerPaymentMethods(String findReference, String findDate){
		return _itemdao.findQtdtotalPerPaymentMethods(findReference, findDate);
	}
	
}
