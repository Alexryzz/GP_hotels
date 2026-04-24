package org.testwork.gp_hotels.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.service.HotelService;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    @PostMapping("/hotels")
    public ResponseEntity<HotelResponse> createHotel(@Valid @RequestBody CreateHotelRequest createHotelRequest){
        System.out.println(createHotelRequest);
        HotelResponse hotelResponse = hotelService.createHotel(createHotelRequest);
        return ResponseEntity.ok(hotelResponse);
    }
}
