package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private Double price;
    private int quantity;
    private String description;
    private String pictures;
    private String videos;
    private String slug;
    private int productId;
    private int category;
    private String fileimg1;
    private String fileimg2;
    private String fileimg3;
    private String filevideo;
}
