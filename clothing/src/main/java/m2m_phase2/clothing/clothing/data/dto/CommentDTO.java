package m2m_phase2.clothing.clothing.data.dto;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.data.entity.CommentE;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.service.UserService;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

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
    private String slugUrl;

//    private static UserService userService;
//
//    public static CommentE convertCommentDtoToCommentE(CommentDTO commentDTO) {
//        return CommentE.builder()
//                .comment(commentDTO.getComment())
//                .user(userService.getUserByEmail())
//                .build();
//    }
}
