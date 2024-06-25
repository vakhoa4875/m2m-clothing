package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopAdminDto {
    private int productId;
    private String productName;
    private float price;
    private int quantity;
    private String description;
    private float averageRate;
    private int rateCount;
    private int sold;
    private String pictures; // Chứa các tấm ảnh được tách bằng dấu phẩy
    private String videos;
    private String slugUrl;
    private int categoryId;
    private int shopId;

}
