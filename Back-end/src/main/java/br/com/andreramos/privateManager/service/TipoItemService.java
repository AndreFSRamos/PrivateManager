package br.com.andreramos.privateManager.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.andreramos.privateManager.exeptionNotFoud.ExeptionNotFound;
import br.com.andreramos.privateManager.model.Tipoitem;
import br.com.andreramos.privateManager.model.modelDAO.TipoitemDAO;

@Service
public class TipoItemService {

	@Autowired
	private TipoitemDAO _tipoitemDAO;
	
	
	
	
	public List<Tipoitem> ToList(){
		return _tipoitemDAO.findAll();
	}
	
	
	
	
	public ResponseEntity<Tipoitem> ToEdit(Short id, Tipoitem newTipoItem){
		Optional<Tipoitem> oldtipoitem = _tipoitemDAO.findById(id);
		
		if(oldtipoitem.isPresent()) {
			Tipoitem tipoitem = oldtipoitem.get();
			tipoitem = newTipoItem;
			_tipoitemDAO.save(tipoitem);
	        return new ResponseEntity<Tipoitem>(tipoitem, HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
	
	public Tipoitem toInsert(Tipoitem newTipoItem) {
		return	_tipoitemDAO.save(newTipoItem);
	}
	
	public ResponseEntity<Object> ToDelete(Short id){
		Optional<Tipoitem> tipoitem = _tipoitemDAO.findById(id);
		
		if(tipoitem.isPresent()) {
			_tipoitemDAO.delete(tipoitem.get());
	        return new ResponseEntity<>(HttpStatus.OK);
		}else {
			throw new ExeptionNotFound();
		}
	}
}
