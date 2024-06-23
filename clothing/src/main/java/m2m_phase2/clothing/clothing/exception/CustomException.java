package m2m_phase2.clothing.clothing.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {
    private final CustomCause customCause;

    public CustomException(CustomCause customCause) {
        super(customCause.getMessage());
        this.customCause = customCause;
    }
}
