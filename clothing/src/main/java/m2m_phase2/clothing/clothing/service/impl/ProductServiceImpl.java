package m2m_phase2.clothing.clothing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.entity.Product;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo repo;
	
	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}

	@Override
	public Product findByproductId(Integer id) {
		return repo.findByproductId(id);
	}
	
	@Override
	public String findCategoryNameByProductId(Integer productId) {
		return repo.findCategoryNameByProductId(productId);
	}
	
	public String[] getProductPictures(Product product) {
		String pathPictures = product.getPictures();
		return pathPictures.split(",");
	}
	
	public String[] getProductDescriptions(Product product) {
		String description = product.getDescription();
		String[] arrayDescriptions = description.split("\\.");
		for (int i = 0 ; i < arrayDescriptions.length; i++) {
			arrayDescriptions[i] =  arrayDescriptions[i].replace("\"","");
		}
		return arrayDescriptions;
	}
	
	public String getProductSold(long sold) {
		
		if(sold < 1000) {
			return String.valueOf(sold);
		}else if(sold < 1000000) {
			return String.format("%.1fK", sold/1000.0);
		}else if(sold < 1000000000) {
			return String.format("%.1fM", sold/1000000.0);
		}else {
			return String.format("%.1fB", sold/1000000000.0);			
		}
	}
	
	public String getProductAverage_rate(double average_rate ){
		StringBuilder htmlStar = new StringBuilder();
		int fullStar = (int) Math.round(average_rate);
		int emptyStar = 5 - fullStar;
		
		for(int i = 0 ; i < fullStar ;i++) {
			htmlStar.append("<li><i class=\"fa fa-star\" aria-hidden=\"true\"></i></li>");
		}
		
		for(int i = 0 ; i < emptyStar ;i++) {
			htmlStar.append("<li><i class=\"fa fa-star-o\" aria-hidden=\"true\"></i></li>");
		}
		
		return htmlStar.toString();
	}
	
	public boolean checkEmply (Product product) {
		if(product.getVideos() != null) {
			if(product.getVideos().length()<0) {
				return false;
			}else {
				return true;
			}
		}
		return false;
	}



}
