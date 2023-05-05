package uz.market.uzum.dtos.user;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidAppErrorDTO implements Serializable {
    private String errorPath;
    private String errorMessage;
    private Integer errorCode;
    private Object errorBody;

    public ValidAppErrorDTO(String errorPath, String errorMessage, Integer errorCode, Object errorBody) {
        this.errorPath = errorPath;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorBody = errorBody;
    }

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
