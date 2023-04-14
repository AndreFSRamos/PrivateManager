package br.com.andreramos.privateManager.model;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;
	
	@Column(length = 255, nullable = false)
	private String description;
	
	@Column( nullable = false)
	private String dateBuy;
	
	@Column(nullable = false, precision = 5, scale=2)
	private BigDecimal price;
	
	@Column(nullable = false)
	private String paymentMethod;
	
	@Column(nullable = false)
	private String category;
}
