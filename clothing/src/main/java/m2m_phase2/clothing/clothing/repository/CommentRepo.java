package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.CommentE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepo  extends JpaRepository<CommentE, Integer> {
    @Query(value = "select * from Comment c where c.product_id  = :product_id", nativeQuery = true)
    List<CommentE> findByProductId(@Param("product_id") int product_id);

    @Modifying
    @Transactional
    @Query(value = "insert into Comment (comment, product_id, user_id, create_date) " +
            "values (:comment, :product_id, :user_id, :create_date)", nativeQuery = true)
    void insertComment(@Param("comment")String comment,
                       @Param("product_id")Integer product_id,
                       @Param("user_id") Long user_id,
                       @Param("create_date") Date create_date);

}



