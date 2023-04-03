package br.com.andreramos.privateManager.model.modelDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andreramos.privateManager.model.Item;

public interface ItemDAO extends JpaRepository<Item, Short>{}
