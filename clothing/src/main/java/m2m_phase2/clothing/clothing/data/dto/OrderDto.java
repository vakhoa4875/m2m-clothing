package m2m_phase2.clothing.clothing.data.dto;


import java.util.Date;

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



    public OrderDto() {
    }

    public OrderDto(Integer orderId, String username, String orderDate, String phoneNumber, String deliveryAddress, String paymentMethod, Float totalAmount, String orderStatus) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.phoneNumber = phoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
    // Getters and setters


    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

