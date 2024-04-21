package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.CommentE;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.CommentM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo  extends JpaRepository<CommentE, Integer> {
    @Query(value = "select * from Comment c where c.product_id  = :product_id", nativeQuery = true)
    List<CommentE> findByProductId(@Param("product_id") int product_id);
}



