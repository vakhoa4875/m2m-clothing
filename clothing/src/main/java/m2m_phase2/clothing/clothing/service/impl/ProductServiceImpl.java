package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.service.ProductService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

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
		return ProductM.convertProductEToProductM(repo.findByslugUrl(slugUrl));
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
	public List<ProductM> findBycategory(Integer categoryId) {
		return ProductM.converListProductEToListProductM(repo.findBycategory(categoryId));
	}

	@Override
	public void insertProduct(ProductDTO productDTO) {
		repo.insertProdudct(
			productDTO.getName(),
			productDTO.getPrice(),
			productDTO.getQuantity(),
			productDTO.getDescription(),
			productDTO.getPictures(),
			productDTO.getVideos(),
			productDTO.getSlug(),
			productDTO.getCategory());
		System.out.println("insert");
	}

	@Override
	public void updateProduct(ProductDTO productDTO) {
		System.out.println(productDTO.toString());
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

	@Override
	public void saveImgAndVideo(ProductDTO productDTO) {
		String stringImage = productDTO.getPictures();
		String[] imageNames = stringImage.split(",");
		if(productDTO.getFileimg1() != null && productDTO.getFileimg2() != null && productDTO.getFileimg3() != null){
			for(int i = 0; i < imageNames.length;i++){
				String base64FromFileImg = null;
				String imageName = imageNames[i].trim();

				if(i == 0){
					if(productDTO.getFileimg1() != null){
						base64FromFileImg = productDTO.getFileimg1().substring(productDTO.getFileimg1().indexOf(",")+1);
					}
				}else if( i == 1 ){
					if(productDTO.getFileimg2() != null) {
						base64FromFileImg = productDTO.getFileimg2().substring(productDTO.getFileimg2().indexOf(",") + 1);
					}
				}else if( i == 2){
					if(productDTO.getFileimg3() != null) {
						base64FromFileImg = productDTO.getFileimg3().substring(productDTO.getFileimg3().indexOf(",") + 1);
					}
				}
				try {
					saveImageAndVideoSentFromClient(imageName, base64FromFileImg);

					System.out.println("lưu ảnh thành công "+ i);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}



		if(productDTO.getFilevideo() != null && !productDTO.getFilevideo().isEmpty()){
			try{
				int viTri = productDTO.getFilevideo().indexOf(",")+1;
				saveImageAndVideoSentFromClient(productDTO.getVideos(), productDTO.getFilevideo().substring(viTri));
				System.out.println("luư video thành công");
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteByProductId(int product_id) {
		repo.deleteByProductId(product_id);
	}

	@Override
	public Long getAllProducts() {
		long count = repo.count();
		return count;
	}

	@Override
	public List<Product> findProductByShopCategory(Integer categoryId, String email) {
		return repo.findProductByShopCategory(categoryId,email);
	}

	@Override
	public List<String[]> getProductsInfoForSearchRecommend() throws SQLException {
		return repo.getAllProducts();
	}

	@Override
	public List<Product> findProductByShopCategoryShopId(Integer categoryId, int shopId) {
		return repo.findProductByShopCategoryShopId(categoryId,shopId);
	}

	private void saveImageAndVideoSentFromClient(String nameImage,String base64FromFileImg) throws FileNotFoundException {
		if(base64FromFileImg != null && !base64FromFileImg.isEmpty()){
			byte[] bytes = Base64.decodeBase64(base64FromFileImg);
			File file = new File("src/main/resources/templates/swappa/assests/media", nameImage);
			FileOutputStream fos = new FileOutputStream(file);
			try {
				fos.write(bytes);
				fos.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

}

