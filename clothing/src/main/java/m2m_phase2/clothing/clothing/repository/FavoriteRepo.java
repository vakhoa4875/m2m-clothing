package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.FavoriteE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FavoriteRepo extends JpaRepository<FavoriteE, Integer> {
    @Query(value = "SELECT f FROM FavoriteE f WHERE f.user.email = :email and f.product.slugUrl = :slugUrl")
    FavoriteE checkFavorite(@Param("email") String email, @Param("slugUrl") String slugUrl);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Favorite (user_id, product_id) VALUES " +
            "(:userId, :productId)", nativeQuery = true)
    int saveFavorite(@Param("userId") Long userId, @Param("productId") Integer productId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Favorite WHERE user_id = :userId AND product_id = :productId", nativeQuery = true)
    int deleteFavorite(@Param("userId") Long userId, @Param("productId") Integer productId);
}
