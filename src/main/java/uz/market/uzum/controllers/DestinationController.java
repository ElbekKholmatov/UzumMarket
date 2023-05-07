package uz.market.uzum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.domains.Destination;
import uz.market.uzum.dtos.destination.DestinationUpdateDTO;
import uz.market.uzum.services.DestinationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/destination")
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping("/ip")
    public ResponseEntity<String> getClientIp(@RequestParam double latitude, @RequestParam double longitude) {
        String address = destinationService.convertToAddress(latitude, longitude);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Destination>> getAll() {
        return ResponseEntity.ok(destinationService.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> getById(@PathVariable Long id) {
        return ResponseEntity.ok(destinationService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> update(@PathVariable Long id, @RequestBody DestinationUpdateDTO destination) {
        Destination updateDestination = destinationService.updateDestination(id, destination);
        return ResponseEntity.ok(updateDestination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = destinationService.deleteDestination(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
