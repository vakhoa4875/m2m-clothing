//package m2m_phase2.clothing.clothing.service.impl;
//
//
//
//import m2m_phase2.clothing.clothing.data.entity.Comment;
//import m2m_phase2.clothing.clothing.data.model.CommentM;
//import m2m_phase2.clothing.clothing.data.model.UserM;
//import m2m_phase2.clothing.clothing.entity.DTO.CommentDTO;
//import m2m_phase2.clothing.clothing.repository.CommentRepo;
//import m2m_phase2.clothing.clothing.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.SQLException;
//import java.util.List;
//
//
//@Service
//public class CommentServiceImpl implements CommentService {
//    @Autowired
//    private CommentRepo commentRepo;
//
//
//    @Override
//    public Comment findCommentsByProductIdAndUserId(CommentDTO commentDTO) throws SQLException {
//        return CommentM.convertUserEToUserM(commentRepo.findCommentsByProductIdAndUserId(commentDTO.getProductId(), commentDTO.getUserId()));
//
//    }
//
//    @Override
//    public Comment findCommentsByProductId(CommentDTO commentDTO) throws SQLException {
//        return CommentM.convertUserEToUserM(commentRepo.findCommentsByProductId( commentDTO.getProductId()));
//    }
//}
