package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.dto.ShopAdminDto;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.entity.Sale;
import m2m_phase2.clothing.clothing.data.entity.ShopE;
import m2m_phase2.clothing.clothing.repository.*;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.ShopAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopAdminServiceImpl implements ShopAdminService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private SaleRepo saleRepo;
    @Autowired
    private ShopAdminRepo shopAdminRepo;


    @Override
    public Product saveProduct(ShopAdminDto shopAdminDto) {
        Product product = new Product();
        product.setProductName(shopAdminDto.getProductName());
        product.setPrice(shopAdminDto.getPrice());
        product.setQuantity(shopAdminDto.getQuantity());
        product.setDescription(shopAdminDto.getDescription());
        product.setAverageRate(shopAdminDto.getAverageRate());
        product.setRateCount(shopAdminDto.getRateCount());
        product.setSold(shopAdminDto.getSold());
        product.setPictures(shopAdminDto.getPictures());
        product.setVideos(shopAdminDto.getVideos());
        product.setSlugUrl(shopAdminDto.getSlugUrl());
        Category category = categoryRepo.findById(shopAdminDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        ShopE shop = shopRepo.findById(shopAdminDto.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        product.setShopE(shop);



        return shopAdminRepo.save(product);
    }

    @Override
    public List<Product> findByShopId(int shopId) {
        return shopAdminRepo.findByShopE_ShopId(shopId);
    }

    @Override
    public Product updateProduct(Product product) {
        // Tìm sản phẩm hiện tại trong cơ sở dữ liệu
        Product existingProduct = productRepo.findById(product.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Cập nhật thông tin sản phẩm hiện tại
        existingProduct.setProductName(product.getProductName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setAverageRate(product.getAverageRate());
        existingProduct.setRateCount(product.getRateCount());
        existingProduct.setSold(product.getSold());
        existingProduct.setPictures(product.getPictures());
        existingProduct.setVideos(product.getVideos());
        existingProduct.setSlugUrl(product.getSlugUrl());
        existingProduct.setCategory(product.getCategory());
        ShopE shop = shopRepo.findById(product.getShopE().getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        existingProduct.setShopE(shop);

        return productRepo.save(existingProduct);
    }
}
