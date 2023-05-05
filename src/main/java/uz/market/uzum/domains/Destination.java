package uz.market.uzum.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String country_code;
    private String residential;
    private String road;
    private String city;
    private String amenity;
    private String county;

    private String aboutPoint;
    private Double longitude;
    private Double latitude;


}
