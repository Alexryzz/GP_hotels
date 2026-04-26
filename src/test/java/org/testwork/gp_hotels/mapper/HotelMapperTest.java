package org.testwork.gp_hotels.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelExtendedResponse;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.entity.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HotelMapperTest {

    private final HotelMapper hotelMapper = Mappers.getMapper(HotelMapper.class);

    @Test
    void toHotel_ShouldMapCreateHotelRequestToHotel() {
        // given
        CreateHotelRequest request = new CreateHotelRequest();
        request.setName("Test Hotel");
        request.setDescription("Test Description");
        request.setBrand("TestBrand");

        Address address = new Address();
        address.setCity("Minsk");
        address.setCountry("Belarus");
        request.setAddress(address);

        Contact contact = new Contact();
        contact.setPhone("+375 17 123-45-67");
        contact.setEmail("test@example.com");
        request.setContacts(contact);

        // when
        Hotel hotel = hotelMapper.toHotel(request);

        // then
        assertThat(hotel).isNotNull();
        assertThat(hotel.getName()).isEqualTo("Test Hotel");
        assertThat(hotel.getDescription()).isEqualTo("Test Description");
        assertThat(hotel.getBrand()).isEqualTo("TestBrand");
        assertThat(hotel.getAddress().getCity()).isEqualTo("Minsk");
        assertThat(hotel.getContacts().getPhone()).isEqualTo("+375 17 123-45-67");
    }

    @Test
    void toHotelResponse_ShouldMapHotelToHotelResponse() {
        // given
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Test Hotel");
        hotel.setDescription("Test Description");

        Address address = new Address();
        address.setCity("Minsk");
        hotel.setAddress(address);

        Contact contact = new Contact();
        contact.setPhone("+375 17 123-45-67");
        hotel.setContacts(contact);

        // when
        HotelResponse response = hotelMapper.toHotelResponse(hotel);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Test Hotel");
        assertThat(response.getPhone()).isEqualTo("+375 17 123-45-67");
        assertThat(response.getAddress()).isEqualTo(address);
    }

    @Test
    void toHotelResponseList_ShouldMapHotelListToHotelResponseList() {
        // given
        Hotel hotel1 = new Hotel();
        hotel1.setId(1L);
        hotel1.setName("Hotel 1");
        Contact contact1 = new Contact();
        contact1.setPhone("+375 17 111-11-11");
        hotel1.setContacts(contact1);

        Hotel hotel2 = new Hotel();
        hotel2.setId(2L);
        hotel2.setName("Hotel 2");
        Contact contact2 = new Contact();
        contact2.setPhone("+375 17 222-22-22");
        hotel2.setContacts(contact2);

        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);

        // when
        List<HotelResponse> responses = hotelMapper.toHotelResponseList(hotels);

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getId()).isEqualTo(1L);
        assertThat(responses.get(0).getPhone()).isEqualTo("+375 17 111-11-11");
        assertThat(responses.get(1).getId()).isEqualTo(2L);
        assertThat(responses.get(1).getPhone()).isEqualTo("+375 17 222-22-22");
    }

    @Test
    void toHotelResponseList_ShouldReturnEmptyList_WhenInputListIsEmpty() {
        // given
        List<Hotel> hotels = List.of();

        // when
        List<HotelResponse> responses = hotelMapper.toHotelResponseList(hotels);

        // then
        assertThat(responses).isEmpty();
    }

    @Test
    void toHotelExtendedResponse_ShouldMapHotelToHotelExtendedResponse() {
        // given
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Test Hotel");
        hotel.setDescription("Test Description");
        hotel.setBrand("TestBrand");

        Address address = new Address();
        address.setCity("Minsk");
        hotel.setAddress(address);

        Contact contact = new Contact();
        contact.setPhone("+375 17 123-45-67");
        contact.setEmail("test@example.com");
        hotel.setContacts(contact);

        ArrivalTime arrivalTime = new ArrivalTime();
        arrivalTime.setCheckIn(LocalTime.of(14, 0));
        arrivalTime.setCheckOut(LocalTime.of(12, 0));
        hotel.setArrivalTime(arrivalTime);

        List<String> amenities = Arrays.asList("Free parking", "Free WiFi");
        hotel.setAmenities(amenities);

        // when
        HotelExtendedResponse response = hotelMapper.toHotelExtendedResponse(hotel);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Test Hotel");
        assertThat(response.getBrand()).isEqualTo("TestBrand");
        assertThat(response.getAddress()).isEqualTo(address);
        assertThat(response.getContacts()).isEqualTo(contact);
        assertThat(response.getArrivalTime()).isEqualTo(arrivalTime);
        assertThat(response.getAmenities()).containsExactly("Free parking", "Free WiFi");
    }
}
