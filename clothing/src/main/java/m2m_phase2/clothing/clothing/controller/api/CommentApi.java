//package m2m_phase2.clothing.clothing.controller.api;
//
//import lombok.extern.slf4j.Slf4j;
//import m2m_phase2.clothing.clothing.data.dto.UserDto;
//import m2m_phase2.clothing.clothing.data.model.CommentM;
//import m2m_phase2.clothing.clothing.data.model.UserM;
//import m2m_phase2.clothing.clothing.entity.DTO.CommentDTO;
//import m2m_phase2.clothing.clothing.service.CommentService;
//import m2m_phase2.clothing.clothing.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.SQLException;
//
//@RestController
//@Slf4j
//@RequestMapping("/api-public/comment")
//public class CommentApi {
//    @Autowired
//    private CommentService commentService;
//
//    @GetMapping("/getCommentByProductIdAndUserId")
//    public ResponseEntity<?> doGetCommentsByProductIdAndUserId(CommentDTO commentDTO){
//        CommentM commentM;
//        try {
//            commentM  = (CommentM) commentService.findCommentsByProductIdAndUserId(commentDTO);
//        } catch (SQLException e) {
//            System.out.println("Call API Failed: /api-public/users/getUserByUsernameAndEmail");
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(commentM);
//    }
//
//    @GetMapping("/getCommentsByProductId")
//    public ResponseEntity<?> doGetCommentsByProductId(CommentDTO commentDTO) {
//        CommentM commentM;
//        try {
//            commentM = (CommentM) commentService.findCommentsByProductId(commentDTO);
//        } catch (SQLException e) {
//            System.out.println("Call API Failed: /api-public/users/getCommentsByProductId");
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(commentM);
//    }
//}
