package m2m_phase2.clothing.clothing.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.service.ProductService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo repo;
	
	@Override
	public List<ProductM> findAll(){
		return ProductM.converListProductEToListProductM(repo.findAll());
	}

	@Override
	public ProductM findByslug_url(String slugUrl) {
		return ProductM.convertProductEToProductM(repo.findByslugUrl( slugUrl));
	}
	
	@Override
	public Category findCategoryNameByProductId(Integer productId) {
		return repo.findCategoryNameByProductId(productId);
	}

	@Override
	public List<Product> findTop6ByOrderByGiaBanDesc() {
		Pageable pageable = PageRequest.of(0, 6); //bắt đầu từ 0 và kích thước 6
		return repo.findTop6ByOrderByGiaBanDesc(pageable);
	}

	@Override
	public List<Product> findProductsWithSaleInfo() {
		return repo.findProductsWithSaleInfo();
  }

	@Override
	public List<Product> findBycategory(Integer categoryId) {
		return repo.findBycategory(categoryId);
	}

	@Override
	public void insertProduct(ProductDTO productDTO) {
		System.out.println(productDTO.toString());
//		repo.insertProdudct(
//			productDTO.getName(),
//			productDTO.getPrice(),
//			productDTO.getQuantity(),
//			productDTO.getDescription(),
//			productDTO.getPictures(),
//			productDTO.getVideos(),
//			productDTO.getSlug(),
//			productDTO.getCategory());
//		System.out.println("insert");
	}

	@Override
	public void updateProduct(ProductDTO productDTO) {
		repo.updateProduct(
				productDTO.getName(),
				productDTO.getPrice(),
				productDTO.getQuantity(),
				productDTO.getDescription(),
				productDTO.getPictures(),
				productDTO.getVideos(),
				productDTO.getCategory(),
				productDTO.getProductId());
		System.out.println("update");
	}

}

