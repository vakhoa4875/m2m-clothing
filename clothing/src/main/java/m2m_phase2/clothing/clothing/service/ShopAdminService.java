package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.dto.ShopAdminDto;
import m2m_phase2.clothing.clothing.data.entity.Product;

import java.util.List;

public interface ShopAdminService {
    Product saveProduct(ShopAdminDto shopAdminDto);
    List<Product> findByShopId(int shopId);
    Product updateProduct(Product product);
}
