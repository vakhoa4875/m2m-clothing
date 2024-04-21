package m2m_phase2.clothing.clothing.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.entity.UserE;

import java.util.Date;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int commentId;
    private String comment;
    private UserE user;
    private Product product;
    private Date createDate;


}
