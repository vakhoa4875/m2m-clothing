package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.ProductM;

import java.util.Date;

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
    private String slugUrl;
}
