package m2m_phase2.clothing.clothing.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.model.CommentM;


import java.sql.SQLException;
import java.util.List;

public interface CommentService {

//    List<CommentM> findByProductId(CommentDTO comment) throws SQLException;

    byte saveComment(CommentDTO commentDTO) throws SQLException;

    byte createComment(CommentDTO commentDTO) throws SQLException;
}

