package m2m_phase2.clothing.clothing.constant;

public enum OrderStatus {
    NEED_APPROVAL("Need approval"),
    APPROVED("Approved"),
    NEED_PAYMENT("Need payment"),
    PAID("Paid"),
    FAILED("Failed"),
    CANCELED("Canceled"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
