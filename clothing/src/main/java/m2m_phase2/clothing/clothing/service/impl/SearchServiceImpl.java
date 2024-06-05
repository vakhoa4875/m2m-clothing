package m2m_phase2.clothing.clothing.service.impl;


import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.repository.SearchRepo;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchRepo searchRepo;

    @Override
    public List<ProductM> searchProducts(String keyword, String type) {
        List<Product> products = searchRepo.searchProducts(keyword.toLowerCase(), type);
        return ProductM.converListProductEToListProductM(products);
    }

    @Override
    public List<ProductM> searchProductsByName(String keyword) {
        List<Product> products = searchRepo.searchProductsFindByName(keyword.toLowerCase());
        return ProductM.converListProductEToListProductM(products);
    }
}
