package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2m_phase2.clothing.clothing.data.entity.OrderDetailE;
import m2m_phase2.clothing.clothing.repository.OrderRepo;
import m2m_phase2.clothing.clothing.repository.ProductRepo;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Integer productId;
    private String slugUrl;
    private String orderCode;
    private Double price;
    private Integer quantity;

    public static OrderDetailE convertOrderDetailDtoToOrderDetailE(OrderDetailDto orderDetailDto, OrderRepo orderRepo, ProductRepo productRepo) {
        return OrderDetailE.builder()
                .order(orderRepo.findByOrderCode(orderDetailDto.orderCode))
                .product(productRepo.findByProductIdOrSlugUrl(orderDetailDto.productId, orderDetailDto.slugUrl))
                .price(orderDetailDto.price)
                .quantity(orderDetailDto.quantity)
                .build();
    }

    public static List<OrderDetailE> convertListOrderDetailDtoToListOrderDetailE(List<OrderDetailDto> orderDetailDtoList, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderDetailDtoList.stream()
                .map(e -> convertOrderDetailDtoToOrderDetailE(e, orderRepo, productRepo))
                .toList();
    }
}
