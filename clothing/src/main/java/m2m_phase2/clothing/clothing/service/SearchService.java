package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.model.ProductM;

import java.util.List;

public interface SearchService {
    List<ProductM> searchProducts(String keyword , String type);
    List<ProductM> searchProductsByName(String keyword);

}
