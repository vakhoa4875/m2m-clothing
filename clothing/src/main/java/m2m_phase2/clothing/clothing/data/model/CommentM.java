package m2m_phase2.clothing.clothing.data.model;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.CommentE;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.entity.UserE;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentM extends CommentE {
    private int commentId;
    private String comment;
    private UserE user;
    private Product product;
    private Date createDate;

    public static CommentM convertUserEToUserM(CommentE comment) {
        return CommentM.builder()
                .commentId(comment.getCommentId())
                .comment(comment.getComment())
                .user(comment.getUser())
                .product(comment.getProduct())
                .createDate(comment.getCreateDate())
                .build();
    }
    public static List<CommentM> converlistCommentEToListCommentM(List<CommentE> listCommentE) {
        return  listCommentE.stream()
                .map(e -> convertUserEToUserM(e))
                .collect(Collectors.toList());
    }
}
