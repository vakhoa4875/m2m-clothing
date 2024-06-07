package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.repository.OrderRepo;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.repository.UserRepo;
import m2m_phase2.clothing.clothing.repository.VoucherRepo;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer orderId;
    private String username;
    private String orderDate;
    private String phoneNumber;
    private String deliveryAddress;
    private String paymentMethod;
    private Float totalAmount;
    private String orderStatus;
    private String soluong;
    private Integer countSp;
    private String orderCode;
    // 034_Khoa
    private Integer voucherId;
    private List<OrderDetailDto> orderDetails;

    public static Order convertOrderDtoToOrder(
            OrderDto orderDto,
//            HttpSession session,
            UserRepo userRepo,
            VoucherRepo voucherRepo,
            OrderRepo orderRepo,
            ProductRepo productRepo
    ) {
        return Order.builder()
                .customer(userRepo.getUserByEmail(PasswordEncoderUtil.email))
                .orderDate(new Date())
                .phoneNumber(orderDto.phoneNumber)
                .deliveryAddress(orderDto.deliveryAddress)
                .paymentMethod(orderDto.paymentMethod)
                .totalAmount(orderDto.totalAmount)
                .orderStatus(orderDto.orderStatus)
                .countSp(orderDto.countSp)
                .orderCode(orderDto.orderCode)
                .voucher(voucherRepo.findByVoucherID(orderDto.voucherId))
                .orderDetails(OrderDetailDto.convertListOrderDetailDtoToListOrderDetailE(orderDto.orderDetails, orderRepo, productRepo))
                .build();
    }

    public void setOrderCode() {
        this.orderCode = PasswordEncoderUtil.email + new Date();
    }
}

