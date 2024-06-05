package m2m_phase2.clothing.clothing.data.mgt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject<T> {
    private String status;
    private String message;
    private T data;
}

