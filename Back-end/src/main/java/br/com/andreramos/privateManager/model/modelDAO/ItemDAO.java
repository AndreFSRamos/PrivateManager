package br.com.andreramos.privateManager.model.modelDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.andreramos.privateManager.model.Item;


public interface ItemDAO extends JpaRepository<Item, Short>{
   @Query(value ="SELECT COUNT(*) As total FROM item WHERE category = :findReference AND date_buy LIKE '%' :findDate '%'", nativeQuery = true)
   int findQtdTotalPerCategory(@Param("findReference") String findReference, @Param("findDate") String findDate);

   @Query(value ="SELECT COUNT(*) As total FROM item WHERE payment_method = :findReference AND date_buy LIKE '%' :findDate '%'", nativeQuery = true)
   int findQtdtotalPerPaymentMethods(@Param("findReference") String findReference, @Param("findDate") String findDate);

}
