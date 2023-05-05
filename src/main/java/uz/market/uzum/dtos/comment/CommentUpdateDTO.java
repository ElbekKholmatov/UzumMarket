package uz.market.uzum.dtos.comment;


import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@ParameterObject
public class CommentUpdateDTO {

    private String text;
    private Byte rate;
    private Long productId;
}
