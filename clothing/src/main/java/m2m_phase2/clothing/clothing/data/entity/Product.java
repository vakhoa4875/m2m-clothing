package m2m_phase2.clothing.clothing.data.entity;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(name = "product_name" , nullable = false)
	private String productName ;

	@Column(name = "price")
	private float price;

	@Column(name = "quantity")
    private int quantity;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "average_rate")
		private float averageRate;
	
	@Column(name = "rate_count")
    private int rateCount;
	
	@Column(name = "sold")
    private int sold;
	
	@Column(name = "pictures")
    private String pictures;
	
	@Column(name = "videos")
    private String videos;
	
	@Column(name = "slug_url")
	private String slugUrl;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne()
	@JoinColumn(name = "sale_ID")
	private Sale sale;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonIgnore
	List<CommentE> comments;

	@Override
	public String toString() {
	    return productName;
	}

}
