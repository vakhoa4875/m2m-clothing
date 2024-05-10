package m2m_phase2.clothing.clothing.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.entity.CommentE;
import m2m_phase2.clothing.clothing.data.model.CommentM;
import m2m_phase2.clothing.clothing.repository.CommentRepo;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.repository.UserRepo;
import m2m_phase2.clothing.clothing.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private HttpSession session;


    @Override
    public List<CommentM> findByProductId(CommentDTO comment) throws SQLException {
        return CommentM.converlistCommentEToListCommentM(commentRepo.findByProductId(comment.getProduct().getProductId()));
    }

    @Override
    public byte saveComment(CommentDTO commentDTO) throws SQLException {
        commentRepo.insertComment(commentDTO.getComment(), commentDTO.getProduct().getProductId(), commentDTO.getUser().getId(), commentDTO.getCreateDate());
        return 1;
    }

    @Override
    public byte createComment(CommentDTO commentDTO) throws SQLException {
//        System.out.println(sessionEmail);
        var product = productRepo.findByslugUrl(commentDTO.getSlugUrl());
        if (product == null) {
            return -1;
        }
        if (sessionEmail == null) {
            // Xử lý khi người dùng chưa đăng nhập
            return -1;
        }
        // Kiểm tra nếu chuỗi loggedInUser không rỗng
        if (sessionEmail != null) {
            // Tìm người dùng bằng email
            var user = userRepo.findByEmail(sessionEmail);
            if (user != null) {
                commentRepo.insertComment(commentDTO.getComment(), product.getProductId(), user.getId(), commentDTO.getCreateDate());
                return 1;
            }
        }

        // Xử lý khi không tìm thấy người dùng hoặc có lỗi xảy ra
        return -1;
    }




}
