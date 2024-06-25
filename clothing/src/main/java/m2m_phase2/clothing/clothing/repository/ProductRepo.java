package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    Product findByslugUrl(String slugUrl);

    Product findByProductId(int productId);

    @Query("SELECT c  FROM Product p JOIN p.category c WHERE p.productId = :productId")
    Category findCategoryNameByProductId(Integer productId);

    @Query("SELECT p FROM Product p LEFT JOIN p.sale s ORDER BY p.sold DESC")
    List<Product> findTop6ByOrderByGiaBanDesc(Pageable pageable); //Pageable được chuyển vào để lấy 6 sản phẩm có giá bán cao nhất

    @Query("SELECT p, s FROM Product p LEFT JOIN p.sale s")
    List<Product> findProductsWithSaleInfo();

    @Query("SELECT p FROM Product p WHERE p.category.category_id = :categoryId")
    List<Product> findBycategory(Integer categoryId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Product (product_name, price, quantity, description, pictures, videos,average_rate, rate_count, sold, slug_url, category_id ) " +
            "VALUES (:name, :price, :quantity, :description, :pictures, :videos, 0 ,0 ,0 ,:slug, :category_id)",
            nativeQuery = true)
    void insertProdudct(@Param("name") String name,
                        @Param("price") double price,
                        @Param("quantity") int quantity,
                        @Param("description") String description,
                        @Param("pictures") String pictures,
                        @Param("videos") String vieods,
                        @Param("slug") String slug,
                        @Param("category_id") int category_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Product SET product_name = :name, price = :price, quantity = :quantity, description = :description, pictures = :pictures, videos = :videos, category_id = :category_id WHERE product_id = :productId", nativeQuery = true)
    void updateProduct(
            @Param("name") String name,
            @Param("price") double price,
            @Param("quantity") int quantity,
            @Param("description") String description,
            @Param("pictures") String pictures,
            @Param("videos") String videos,
            @Param("category_id") int category_id,
            @Param("productId") int product_id);

    @Transactional
    void deleteByProductId(int product_id);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.category_id = :categoryId")
    Long countByCategory(@Param("categoryId") Integer categoryId);

    Product findByProductIdOrSlugUrl(Integer productId, String slugUrl);

    @Query("SELECT p FROM Product p WHERE p.category.category_id = :categoryId and p.shopE.userE.email = :email")
    List<Product> findProductByShopCategory(@Param("categoryId") Integer categoryId,
                                            @Param("email") String email);

    @Query("SELECT p.shopE.shopId FROM Product p WHERE p.productId = :productId")
    Integer findShopIdByProductId(@Param("productId") Integer productId);

    @Query(nativeQuery = true, value = "exec dbo.GetShopDetails :shop_id")
    List<Object[]> GetShopDetails(@Param("shop_id") int shop_id);
    @Query("select p.productName, p.slugUrl from Product p")
    List<String[]> getAllProducts();

    @Query("SELECT p FROM Product p WHERE p.category.category_id = :categoryId and p.shopE.shopId = :shopId")
    List<Product> findProductByShopCategoryShopId(@Param("categoryId") Integer categoryId,
                                            @Param("shopId") int shopId);
}
