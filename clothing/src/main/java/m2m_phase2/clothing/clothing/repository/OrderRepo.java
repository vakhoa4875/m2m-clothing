package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAll();
    @Query("SELECT o.orderId, u.username, o.orderDate, o.phoneNumber, o.deliveryAddress, o.paymentMethod, o.totalAmount, o.orderStatus " +
            "FROM Order o JOIN o.customer u WHERE u.email = :email")
    List<Object[]> findOrdersWithUsernameByEmail(@Param("email") String email);
    @Modifying
    @Query(value = "update Order " +
            "set order_status = :paymentStatus " +
            "where order_id = :orderId", nativeQuery = true)
    byte updatePaymentStatusByOrderId(@Param("paymentStatus") String paymentStatus, @Param("orderId") Integer orderId);

    @Modifying
    @Transactional
    @Query(value = "insert into [Order] (customer_id, phone_number, delivery_address, payment_method, total_amount, order_status)"
            + "values(:customer_id, :phone_number, :delivery_address, :payment_method, :total_amount, :order_status)", nativeQuery = true
    )
    void inserOder(@Param("customer_id") Integer customer_id, @Param("phone_number") String phone_number,
                   @Param("delivery_address") String delivery_address, @Param("payment_method") String payment_method, @Param("total_amount") float total_amount,
                   @Param("order_status") String order_status);


}
