package uz.market.uzum.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ParameterObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DestinationUpdateDTO {
    private String country;
    private String country_code;
    private String residential;
    private String road;
    private String city;
    private String amenity;
    private String county;
    @NotNull(message = "longitude cannot be null")
    private Double longitude;
    @NotNull(message = "latitude cannot be null")
    private Double latitude;
}
