package m2m_phase2.clothing.clothing.entity.DTO;

import jakarta.persistence.*;
import m2m_phase2.clothing.clothing.entity.Product;

import java.util.List;

public class CategoryDTO {

	private int category_id ;

	private String category_name;

	private String logo;

	private String description;

	private List<Product> produts;

	public CategoryDTO(int category_id, String category_name, String logo, String description, List<Product> produts) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.logo = logo;
		this.description = description;
		this.produts = produts;
	}

	public CategoryDTO() {
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProduts() {
		return produts;
	}

	public void setProduts(List<Product> produts) {
		this.produts = produts;
	}
}
