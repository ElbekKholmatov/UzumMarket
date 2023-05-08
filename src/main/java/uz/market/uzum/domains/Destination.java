package uz.market.uzum.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class Destination extends Auditable {
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

    private Double longitude;
    private Double latitude;

    @Builder(builderMethodName = "childbuilder")
    public Destination(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Long id, String country, String country_code, String residential, String road, String city, String amenity, String county, Double longitude, Double latitude) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.country = country;
        this.country_code = country_code;
        this.residential = residential;
        this.road = road;
        this.city = city;
        this.amenity = amenity;
        this.county = county;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
