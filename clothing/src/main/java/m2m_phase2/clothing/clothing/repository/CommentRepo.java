//package m2m_phase2.clothing.clothing.repository;
//
//import m2m_phase2.clothing.clothing.data.entity.Comment;
//import m2m_phase2.clothing.clothing.data.model.CommentM;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface CommentRepo  extends JpaRepository<Comment, Integer> {
//    @Query(value = "SELECT c.comment, p.productName, u.fullname, c.createDate " +
//            "FROM Product p LEFT JOIN Comment c ON p.productId = c.product.productId " +
//            "left JOIN [user]  u ON c.userId = u.id " +
//            "WHERE p.productId = :productId AND u.id = :userId" , nativeQuery = true)
//    CommentM findCommentsByProductIdAndUserId(@Param("productId") int productId, @Param("userId") int userId);
//
//
//    @Query( value = "SELECT c.comment, p.productName, u.fullname, c.createDate " +
//            "FROM Product p LEFT JOIN Comment c ON p.productId = c.product.productId " +
//            "JOIN [user] u ON c.userId = u.id " +
//            "WHERE p.productId = :productId" , nativeQuery = true)
//    CommentM findCommentsByProductId(@Param("productId") int productId);
//}
//
//
//
