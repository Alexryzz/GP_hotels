package org.testwork.gp_hotels.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelExtendedResponse;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.service.HotelService;
import java.util.List;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;
    @PostMapping("/hotels")
    public ResponseEntity<HotelResponse> createHotel(@Valid @RequestBody CreateHotelRequest createHotelRequest){
        log.info("create hotel request");
        HotelResponse hotelResponse = hotelService.createHotel(createHotelRequest);
        return ResponseEntity.ok(hotelResponse);
    }
    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenities(@PathVariable Long id,
                                               @RequestBody List<String> amenities){
        log.info("add amenities request");
        hotelService.addAmenitiesToHotel(id, amenities);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/hotels")
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        log.info("get hotels request");
        List<HotelResponse> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelExtendedResponse> getHotel(@PathVariable Long id){
        log.info("get hotel request");
        HotelExtendedResponse hotelExtendedResponse = hotelService.getExtendedInfoOfHotel(id);
        return ResponseEntity.ok(hotelExtendedResponse);
    }
}
