package m2m_phase2.clothing.clothing.constant;

public enum AccountEnum {
    not_matched("The confirm password is not matched! Please check again!"),
    existed("The login credentials are already used! Please check again!"),
    succeed("Create account successfully!"),
    error("An error occured!");

    private final String value;

    AccountEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
