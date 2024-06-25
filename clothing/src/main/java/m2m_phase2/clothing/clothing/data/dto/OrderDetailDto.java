package m2m_phase2.clothing.clothing.data.dto;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.OrderDetailE;
import m2m_phase2.clothing.clothing.exception.CustomCause;
import m2m_phase2.clothing.clothing.exception.CustomException;
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

    @SneakyThrows
    public static OrderDetailE convertOrderDetailDtoToOrderDetailE(OrderDetailDto orderDetailDto, OrderRepo orderRepo, ProductRepo productRepo) {
        var product = productRepo.findByProductIdOrSlugUrl(orderDetailDto.productId, orderDetailDto.slugUrl);
        if (product != null) {
            if (product.getQuantity() < orderDetailDto.quantity) {
                var cause = CustomCause.INVALID_QUANTITY;
                cause.setMessage("Invalid product quantity, only "+ product.getQuantity() +" left on stocks");
                throw new CustomException(cause);
            }
        }
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
