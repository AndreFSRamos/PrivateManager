package br.com.andreramos.privateManager.model.modelDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andreramos.privateManager.model.Categories;

public interface CategoriesDAO extends JpaRepository<Categories,Short>{}
