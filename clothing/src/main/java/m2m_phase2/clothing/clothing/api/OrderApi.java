package m2m_phase2.clothing.clothing.api;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.OrderDto;
import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpSession session;
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderService.findAllOrders();
//        if (orders.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrdersWithUsernameByCustomerEmail(HttpSession session) {
        String userEmail = (String) session.getAttribute("loggedInUser");
        if (userEmail == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Object[]> ordersWithUsername = orderService.findOrdersWithUsernameByEmail(userEmail);
        if (ordersWithUsername.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Chuyển đổi dữ liệu từ Object[] sang OrderDto
        List<OrderDto> dtos = new ArrayList<>();
        for (Object[] obj : ordersWithUsername) {
            OrderDto dto = new OrderDto();
            dto.setOrderId((Long) obj[0]);
            dto.setUsername((String) obj[1]);
            dto.setOrderDate((Date) obj[2]);
            dto.setPhoneNumber((String) obj[3]);
            dto.setDeliveryAddress((String) obj[4]);
            dto.setPaymentMethod((String) obj[5]);
            dto.setTotalAmount((Double) obj[6]);
            dto.setOrderStatus((String) obj[7]);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

//    @PostMapping("/paypal/paid")
}
