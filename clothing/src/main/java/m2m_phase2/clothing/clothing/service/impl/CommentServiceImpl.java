package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.model.CommentM;
import m2m_phase2.clothing.clothing.repository.CommentRepo;
import m2m_phase2.clothing.clothing.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;


    @Override
        public List<CommentM> findByProductId(CommentDTO comment) throws SQLException {
        return CommentM.converlistCommentEToListCommentM(commentRepo.findByProductId(comment.getProduct().getProductId()));
    }
}
