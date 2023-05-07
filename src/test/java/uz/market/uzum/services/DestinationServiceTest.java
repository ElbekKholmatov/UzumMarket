package uz.market.uzum.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.Destination;
import uz.market.uzum.dtos.destination.DestinationUpdateDTO;
import uz.market.uzum.repositories.DestinationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {DestinationService.class})
@ExtendWith(SpringExtension.class)
class DestinationServiceTest {

    @MockBean
    private DestinationRepository destinationRepository;

    @Autowired
    private DestinationService destinationService;


    @Test
    void findAll() {
        List<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination(1L, "USA", "US", "Residential", "Main St", "New York", "Amenity", "County", 40.7128, -74.0060));
        destinations.add(new Destination(2L, "Canada", "CA", "Residential", "Queen St", "Toronto", "Amenity", "County", 43.6532, -79.3832));
        when(destinationRepository.findAll()).thenReturn(destinations);
        List<Destination> result = destinationService.findAll();
        assertEquals(destinations, result);
    }

    @Test
    void updateDestination() {

        Destination destination = new Destination(1L, "USA", "US", "Residential", "Main St", "New York", "Amenity", "County", 40.7128, -74.0060);
        DestinationUpdateDTO destinationUpdateDTO = new DestinationUpdateDTO("Canada", "CA", "Commercial", "King St", "Toronto", "Amenity", "County", 43.6532, -79.3832);

        when(destinationRepository.findById(destination.getId())).thenReturn(Optional.of(destination));
        when(destinationRepository.save(destination)).thenReturn(destination);

        Destination result = destinationService.updateDestination(destination.getId(), destinationUpdateDTO);
        assertEquals(destination.getId(), result.getId());
        assertEquals(destinationUpdateDTO.getCountry(), result.getCountry());
        assertEquals(destinationUpdateDTO.getCountry_code(), result.getCountry_code());
        assertEquals(destinationUpdateDTO.getResidential(), result.getResidential());
        assertEquals(destinationUpdateDTO.getRoad(), result.getRoad());
        assertEquals(destinationUpdateDTO.getCity(), result.getCity());
        assertEquals(destinationUpdateDTO.getAmenity(), result.getAmenity());
        assertEquals(destinationUpdateDTO.getCounty(), result.getCounty());
        assertEquals(destinationUpdateDTO.getLatitude(), result.getLatitude());
        assertEquals(destinationUpdateDTO.getLongitude(), result.getLongitude());
    }

//    @Test
//    void deleteDestination() {
//        Destination destination = new Destination();
//        destination.setLatitude(48.858093);
//        destination.setLongitude(2.294694);
//        destination.setCity("Paris");
//        destination.setCountry("France");
//        Destination savedDestination = destinationRepository.save(destination);
//        boolean result = destinationService.deleteDestination(savedDestination.getId());
//        Assertions.assertTrue(result);
//        Optional<Destination> deletedDestination = destinationRepository.findById(savedDestination.getId());
//        Assertions.assertFalse(deletedDestination.isPresent());
//    }
}