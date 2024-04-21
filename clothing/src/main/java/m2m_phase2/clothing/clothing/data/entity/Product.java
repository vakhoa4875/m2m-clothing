package m2m_phase2.clothing.clothing.data.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
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
	@JsonBackReference
	private Category category;

	@ManyToOne()
	@JoinColumn(name = "sale_ID")
	private Sale sale;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	List<CommentE> comments;

	@Override
	public String toString() {
	    return productName;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(float averageRate) {
		this.averageRate = averageRate;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}
	
	public String getSlugUrl() {
		return slugUrl;
	}

	public void setSlugUrl(String slugUrl) {
		this.slugUrl = slugUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	
}
