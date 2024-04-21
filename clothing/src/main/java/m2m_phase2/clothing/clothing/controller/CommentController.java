//package m2m_phase2.clothing.clothing.controller;
//
//import lombok.RequiredArgsConstructor;
//import m2m_phase2.clothing.clothing.data.entity.Comment;
//import m2m_phase2.clothing.clothing.service.CommentService;
//import m2m_phase2.clothing.clothing.service.impl.CommentServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/apiprefix/comment")
//@RequiredArgsConstructor
//public class CommentController {
//    private final CommentServiceImpl commentServiceImpl;
//
//    @Autowired
//    private CommentService commentService;
//
////    @GetMapping("/comments")
////        public ResponseEntity<List<Comment>> getAllComments(
////            @RequestParam("user_id") int userId,
////            @RequestParam("product_id") int productId)
////        {
////            List<Comment> comments = commentServiceImpl.getCommentsByUserIdAndProductId(userId, productId);
////            return ResponseEntity.ok(comments);
////        }
//
//}
