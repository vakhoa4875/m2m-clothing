//package m2m_phase2.clothing.clothing.data.model;
//
//import lombok.*;
//import m2m_phase2.clothing.clothing.data.entity.Comment;
//import m2m_phase2.clothing.clothing.data.entity.UserE;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class CommentM extends Comment {
//    private int comment_id;
//    private String comment;
//    private int user_id;
//    private int product_id;
//    private Date comment_date;
//
//    public static CommentM convertUserEToUserM(Comment comment) {
//        return CommentM.builder()
//                .comment_id(comment.getCommentId())
//                .comment(comment.getComment())
//                .product_id(comment.getProductId())
//                .user_id(comment.getUserId())
//                .build();
//    }
//    public static List<CommentM> converlistCommentEToListCommentM(List<Comment> listCommentE) {
//        return  listCommentE.stream()
//                .map(e -> convertUserEToUserM(e))
//                .collect(Collectors.toList());
//    }
//}
