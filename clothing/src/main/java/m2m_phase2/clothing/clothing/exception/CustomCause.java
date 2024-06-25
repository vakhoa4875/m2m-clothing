package m2m_phase2.clothing.clothing.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CustomCause {
    ERROR(1001, "Error occurred while processing"),
    INVALID_QUANTITY(1004, "Invalid product quantity, only 1 left stocks");
    private int code;
    @Setter
    private String message;

    CustomCause(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
