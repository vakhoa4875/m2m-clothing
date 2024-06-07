//package m2m_phase2.clothing.clothing.data.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import m2m_phase2.clothing.clothing.data.entity.OrderDetailE;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class OrderDetailM {
//    private int order_detail_id;
//    private String nameproduct;
//    private int quatity;
//    private float toal_product;
//
//    public static OrderDetailM convertOrderDetailEToOrderDetailM(OrderDetailE orderDetailE) {
//        return OrderDetailM.builder()
//                .order_id_detail(orderDetailE.getOrder_id_detail())
//                .nameproduct(orderDetailE.getNameproduct())
//                .quatity(orderDetailE.getQuatity())
//                .toal_product(orderDetailE.getToal_product())
//                .build();
//    }
//
//    public static List<OrderDetailM> converListOrderDetailEToListOrderDetailM(List<OrderDetailE> listOrderDetailE) {
//        return  listOrderDetailE.stream()
//                .map(e -> convertOrderDetailEToOrderDetailM(e))
//                .collect(Collectors.toList());
//    }
//}
