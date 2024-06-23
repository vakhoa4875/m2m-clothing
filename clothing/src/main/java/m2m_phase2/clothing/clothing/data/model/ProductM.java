package m2m_phase2.clothing.clothing.data.model;

import lombok.Builder;
import lombok.Data;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.entity.Sale;
import m2m_phase2.clothing.clothing.data.entity.ShopE;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProductM {
    private int productId;
    private String name;
    private Float price;
    private int quantity;
    private String description;
    private String pictures;
    private String videos;
    private String slug;
    private Category category;
    private Sale sale;
    private ShopE shop;
    private float averageRate;
    private int rateCount;
    private int sold;

    public static ProductM convertProductEToProductM(Product productE){
        return ProductM.builder()
                .name(productE.getProductName())
                .price(productE.getPrice())
                .quantity(productE.getQuantity())
                .description(productE.getDescription())
                .pictures(productE.getPictures())
                .videos(productE.getVideos())
                .slug(productE.getSlugUrl())
                .productId(productE.getProductId())
                .category(productE.getCategory())
                .sale(productE.getSale())
                .shop(productE.getShopE())
                .averageRate(productE.getAverageRate())
                .rateCount(productE.getRateCount())
                .sold(productE.getSold())
                .build();
    }

    public static List<ProductM> converListProductEToListProductM(List<Product> listProductE){
        return listProductE.stream()
                .map(e ->convertProductEToProductM(e))
                .collect(Collectors.toList());
    }
}
