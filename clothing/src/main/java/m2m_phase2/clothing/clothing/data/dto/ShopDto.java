package m2m_phase2.clothing.clothing.data.dto;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    private String productName; // Tên
    private float price;    // Giá
    private int quantity;   // Số lượng
    private String description; // Mô tả
    private float averageRate; // Đánh giá
    private int rateCount; // Số lượng đánh giá
    private int sold; // Số lượng đã bán
    private String pictures; // Chứa các tấm ảnh được tách bằng dấu phẩy
    private String videos; // videos
    private String slugUrl; // Bằng với tên
    private int categoryId;
    private int saleId;
=======
import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.UserE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    private Integer shopId;

    private String logo;

    private String nameShop;

    private String dateEstablished;

    private UserE userE;
>>>>>>> main
}
