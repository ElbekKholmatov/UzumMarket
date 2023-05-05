package uz.market.uzum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.services.DestinationService;

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



}
