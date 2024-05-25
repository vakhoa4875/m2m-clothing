package m2m_phase2.clothing.clothing.data.dto;

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
}
