package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAll();

    @Query("SELECT o.orderId, u.username, o.orderDate, o.phoneNumber, o.deliveryAddress, o.paymentMethod, o.totalAmount, o.orderStatus " +
            "FROM Order o JOIN o.customer u WHERE u.email = :email")
    List<Object[]> findOrdersWithUsernameByEmail(@Param("email") String email);


//    byte updatePaymentStatusByOrderId(@Param("paymentStatus") String paymentStatus, @Param("orderId") Integer orderId);

    @Modifying
    @Transactional
    @Query(value = "insert into [Order] (customer_id, phone_number, delivery_address, payment_method, total_amount, order_status)"
            + "values(:customer_id, :phone_number, :delivery_address, :payment_method, ROUND(:total_amount, 2), :order_status)", nativeQuery = true
    )
    void inserOder(@Param("customer_id") Integer customer_id, @Param("phone_number") String phone_number,
                   @Param("delivery_address") String delivery_address, @Param("payment_method") String payment_method, @Param("total_amount") float total_amount,
                   @Param("order_status") String order_status);


    @Modifying
    @Query(value = "select o.order_id, " +
            "u.fullname, " +
            "o.order_date, " +
            "o.phone_number, " +
            "o.delivery_address, " +
            "o.payment_method, " +
            "ROUND(o.total_amount, 2), " +
            "o.count_sp, " +
            "o.order_status " +
            "from [Order] o " +
            "left join dbo.order_detail od on o.order_id = od.order_detail_id " +
            "join dbo.[user] u on u.id = o.customer_id",
            nativeQuery = true)
    List<Object[]> findAllUser();

    @Modifying
    @Transactional
    @Query(value = "update [Order] " +
            "set order_status = :orderStatus " +
            "where order_id = :orderId", nativeQuery = true)
    int updateOrderStatus(@Param("orderId") Integer orderId, @Param("orderStatus") String orderStatus);

    //    @Override
    Optional<Order> findById(Integer id);

    @Transactional
    Order save(Order order);

    Order findByOrderCode(String orderCode);
}
