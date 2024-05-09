package m2m_phase2.clothing.clothing.api;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.OrderDto;
import m2m_phase2.clothing.clothing.service.OrderDetailService;
import m2m_phase2.clothing.clothing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-product")
public class OrderApi {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
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

    @GetMapping("/orders")
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
            dto.setOrderId((Integer) obj[0]);
            dto.setUsername((String) obj[1]);
            dto.setOrderDate(obj[2].toString());
            dto.setPhoneNumber((String) obj[3]);
            dto.setDeliveryAddress((String) obj[4]);
            dto.setPaymentMethod((String) obj[5]);
            dto.setTotalAmount((Float) obj[6]);
            dto.setOrderStatus((String) obj[7]);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    };

    @GetMapping("/ordersAllProctStatus")
    public ResponseEntity<?> getAllProctStatus() {
        List<Object[]> ordersWithUsername = orderService.findAllUser();
        // Chuyển đổi dữ liệu từ Object[] sang OrderDto
        List<OrderDto> dtos = new ArrayList<>();
        for (Object[] obj : ordersWithUsername) {
            OrderDto dto = new OrderDto();
            dto.setOrderId((Integer) obj[0]);
            dto.setUsername((String) obj[1]);
            dto.setOrderDate(obj[2].toString());
            dto.setPhoneNumber((String) obj[3]);
            dto.setDeliveryAddress((String) obj[4]);
            dto.setPaymentMethod((String) obj[5]);
            dto.setTotalAmount(Float.valueOf((float) ((Double) obj[6]).doubleValue()));
            dto.setSoluong(String.valueOf(obj[7]));
            dto.setOrderStatus((String) obj[8]);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    };

    @PostMapping("/saveOder")
    public ResponseEntity<?> saveOderUser(@RequestBody OrderDto orderDto){
        try{
           orderService.inserOder(orderDto);
        }catch (Exception e){
            System.out.println("Call API Failed: /api/orders/saveOder");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping("/insertOderdetail")
    public ResponseEntity<?> insertOder(@RequestBody OrderDto orderDto){
        orderDto.setOrderId(orderDetailService.getLastInsertedOrderId());
        try{
            orderDetailService.UpdateOderDetail(orderDto);
        }catch (Exception e){
            System.out.println("Call API Failed: /api/orders/saveOder");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/updateOderUser")
    public ResponseEntity<?> updateOdeUserfromAdmin(@Param("idProduct") Integer idProduct,@Param("OrderStatus") String OrderStatus){
        try{
            orderService.updateOrderStatusByOrderId(idProduct,OrderStatus);
            return ResponseEntity.ok("Cập nhật thành id đơn hàng " +idProduct+ " thành công");
        }catch (Exception e){
            System.out.println("Call API Failed: /api/orders/saveOder");
            throw new RuntimeException(e);
        }
    }

}
