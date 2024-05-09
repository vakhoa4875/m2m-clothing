package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.model.CommentM;


import java.sql.SQLException;
import java.util.List;

public interface CommentService {

    List<CommentM> findByProductId(CommentDTO comment) throws SQLException;
}

