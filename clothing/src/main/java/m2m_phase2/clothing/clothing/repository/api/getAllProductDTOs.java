package m2m_phase2.clothing.clothing.repository.api;

import m2m_phase2.clothing.clothing.entity.DTO.ProductDTO;
import m2m_phase2.clothing.clothing.entity.Product;
import m2m_phase2.clothing.clothing.service.impl.CategoryImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class getAllProductDTOs {
    @Autowired
    private ProductServiceImpl productserviceimpl;

    @Autowired
    private CategoryImpl categoryimpl;


    @GetMapping("/allproductDTOapi")
    public List<ProductDTO> getAllProductDTOS(){
        List<Product> productList = productserviceimpl.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for(Product product : productList){
            ProductDTO productDTO = new ProductDTO(
                    product.getProductId(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getDescription(),
                    product.getAverageRate(),
                    product.getRateCount(),
                    product.getSold(),
                    product.getPictures(),
                    product.getVideos(),
                    product.getCategory()
            );
            productDTO.setCategory(null);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

}
