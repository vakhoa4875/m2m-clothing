package m2m_phase2.clothing.clothing.data.dto;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.ProductM;

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
    private ProductM product;
    private Date createDate;

}
