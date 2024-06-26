package m2m_phase2.clothing.clothing.constant;

public enum CommonEnum {
    imagesUser("src/main/resources/templates/swappa/assests/imagesUser");

    private final String value;

    CommonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
