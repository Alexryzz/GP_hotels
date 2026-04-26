package org.testwork.gp_hotels.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.entity.Address;
import org.testwork.gp_hotels.entity.ArrivalTime;
import org.testwork.gp_hotels.entity.Contact;
import org.testwork.gp_hotels.service.HotelService;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private HotelService hotelService;

    @Test
    void createHotel_ShouldReturnCreatedHotel() throws Exception {
        // given
        CreateHotelRequest request = new CreateHotelRequest();
        request.setName("DoubleTree by Hilton Minsk");
        request.setBrand("Hilton");
        request.setDescription("Luxury hotel in Minsk");

        Address address = new Address();
        address.setHouseNumber("9");
        address.setStreet("Pobediteley Avenue");
        address.setCity("Minsk");
        address.setCountry("Belarus");
        address.setPostCode("220004");
        request.setAddress(address);

        Contact contact = new Contact();
        contact.setPhone("+375 17 309-80-00");
        contact.setEmail("doubletreeminsk.info@hilton.com");
        request.setContacts(contact);

        ArrivalTime arrivalTime = new ArrivalTime();
        arrivalTime.setCheckIn(LocalTime.of(14, 0));
        arrivalTime.setCheckOut(LocalTime.of(12, 0));
        request.setArrivalTime(arrivalTime);

        HotelResponse response = new HotelResponse();
        response.setId(1L);
        response.setName("DoubleTree by Hilton Minsk");
        response.setDescription("Luxury hotel in Minsk");
        response.setPhone("+375 17 309-80-00");
        response.setAddress(address);

        when(hotelService.createHotel(any(CreateHotelRequest.class))).thenReturn(response);

        // when & then
        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("DoubleTree by Hilton Minsk"))
                .andExpect(jsonPath("$.phone").value("+375 17 309-80-00"))
                .andExpect(jsonPath("$.address").value(containsString("Minsk")));
    }

    @Test
    void createHotel_ShouldReturnBadRequest_WhenNameIsMissing() throws Exception {
        // given
        CreateHotelRequest request = new CreateHotelRequest();
        request.setBrand("Hilton");

        Contact contact = new Contact();
        contact.setPhone("+375 17 309-80-00");
        request.setContacts(contact);

        // when & then
        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addAmenities_ShouldReturnOk() throws Exception {
        // given
        Long hotelId = 1L;
        List<String> amenities = Arrays.asList("Free parking", "Free WiFi");

        doNothing().when(hotelService).addAmenitiesToHotel(eq(hotelId), any(List.class));

        // when & then
        mockMvc.perform(post("/property-view/hotels/{id}/amenities", hotelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(amenities)))
                .andExpect(status().isOk());
    }
}
