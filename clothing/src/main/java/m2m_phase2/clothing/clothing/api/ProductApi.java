package m2m_phase2.clothing.clothing.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.service.impl.CategoryImpl;
import m2m_phase2.clothing.clothing.service.impl.CommentServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;


@RestController
public class ProductApi {
	
	@Autowired
	private ProductServiceImpl productserviceimpl;

	@Autowired
	private CommentServiceImpl commentService;


	@Autowired
	private CategoryImpl categoryimpl;
	
	@GetMapping("/allproductapi")
	public List<ProductM> getAllProduct() {
		return productserviceimpl.findAll();
	}
	
	@GetMapping("/findbyproductidapi")
	public ProductM getfindbyproductid(@RequestParam String slug_url) {
		return productserviceimpl.findByslug_url(slug_url);
	}
	
	@GetMapping("/findcategorynamebyproductidapi")
	public Category getfindcategorynamebyproductid(@RequestParam Integer product_id) {
		return productserviceimpl.findCategoryNameByProductId(product_id);
	}
	
	@GetMapping("/allcategoryapi")
	public List<Category> getallcategoryapi() {
		return categoryimpl.findAll();
	}

	@GetMapping("/findTop6ByOrderByGiaBanDesc")
	public List<Product> findTop6ByOrderByGiaBanDesc(){
		return productserviceimpl.findTop6ByOrderByGiaBanDesc();
	}


	@GetMapping("/sale")
	public List<Product> productWithSaleInfo() {
		return productserviceimpl.findProductsWithSaleInfo();
	}

	@PostMapping("/insertProduct")
	public ResponseEntity<?> insertProduct(@RequestBody ProductDTO productDTO){
		try {
			productserviceimpl.insertProduct(productDTO);
			productserviceimpl.saveImgAndVideo(productDTO);
		} catch (Exception e) {
			System.out.println("Call API Failed: /insertProduct");
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok("Product inserted successfully.");

	}

	@PostMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO){
		try {
			productserviceimpl.updateProduct(productDTO);
			productserviceimpl.saveImgAndVideo(productDTO);
		} catch (Exception e) {
			System.out.println("Call API Failed: /update");
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok("Product update successfully.");
	}

	@DeleteMapping("/products/{product_id}")
	public void deleteProductById(@PathVariable("product_id") int productId) {
		productserviceimpl.deleteByProductId(productId);
	}

	@PostMapping("/saveComment")
	public ResponseEntity<?> doPostSaveComment(@RequestBody CommentDTO commentDTO) {
		byte rowEffected;
		try {
			rowEffected = commentService.saveComment(commentDTO);
		} catch (Exception e) {
			System.out.println("Call API Failed: /saveComment");
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok(rowEffected);
	}

	@PostMapping("/createComment")
	public ResponseEntity<?> doPostCreateComment(@RequestBody CommentDTO commentDTO) {
		byte rowEffected;
		try {
			rowEffected = commentService.createComment(commentDTO);
		} catch (Exception e) {
			System.out.println("Call API Failed: /createComment");
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok(rowEffected);
	}

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	@GetMapping("/testloginwithgg")
	public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		Map<String, Object> userInfo = new HashMap<>();

		//lay thong tin nguoi dung
		Map<String , Object> attributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
		userInfo.putAll(attributes);

		//lay accesstoken
		String clientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
		OAuth2AuthorizedClient oAuth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(clientRegistrationId,oAuth2AuthenticationToken.getName());
		OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();
		userInfo.put("access_token", accessToken.getTokenValue());

		//lay danh sach quyen truy cap
		userInfo.put("scopes", accessToken.getScopes());

		return  userInfo;

//		return oAuth2AuthenticationToken.getPrincipal().getAttributes();
	}

	@GetMapping("/api-public-getListProductByCategoryAndShopEmail")
	public ResponseEntity<?> findProductByShopCategory(@RequestParam Integer categoryId){
		Map<String, Object> result = new HashMap<>();
		try {
			result.put("status", true);
			result.put("message", "Call Api Success");
			result.put("data", productserviceimpl.findProductByShopCategory(categoryId,sessionEmail));
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", "Call Api Fail");
			result.put("data", null);
		}
		return ResponseEntity.ok(result);
	}


}
