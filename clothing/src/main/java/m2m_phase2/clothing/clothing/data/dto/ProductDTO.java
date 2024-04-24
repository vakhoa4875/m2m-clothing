package m2m_phase2.clothing.clothing.data.dto;

import lombok.Data;

@Data

public class ProductDTO {
    private String name;
    private Double price;
    private int quantity;
    private String description;
    private String pictures;
    private String videos;
    private String slug;
    private int productId;


}
