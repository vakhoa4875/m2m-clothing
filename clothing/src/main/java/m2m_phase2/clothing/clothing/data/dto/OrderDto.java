package m2m_phase2.clothing.clothing.data.dto;


import java.util.Date;

public class OrderDto {
    private Long orderId;
    private String username;
    private Date orderDate;
    private String phoneNumber;
    private String deliveryAddress;
    private String paymentMethod;
    private Double totalAmount;
    private String orderStatus;



    public OrderDto() {
    }

    public OrderDto(Long orderId, String username, Date orderDate, String phoneNumber, String deliveryAddress, String paymentMethod, Double totalAmount, String orderStatus) {
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

