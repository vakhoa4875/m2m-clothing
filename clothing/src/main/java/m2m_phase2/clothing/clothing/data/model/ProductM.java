package m2m_phase2.clothing.clothing.data.model;

import lombok.Builder;
import lombok.Data;
import m2m_phase2.clothing.clothing.data.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProductM {
    private String name;
    private Float price;
    private int quantity;
    private String description;
    private String pictures;
    private String videos;
    private String slug;
    private int productId;
    private int category;

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
                .category(productE.getCategory().getCategory_id())
                .build();
    }

    public static List<ProductM> converListProductEToListProductM(List<Product> listProductE){
        return listProductE.stream()
                .map(e ->convertProductEToProductM(e))
                .collect(Collectors.toList());
    }
}
