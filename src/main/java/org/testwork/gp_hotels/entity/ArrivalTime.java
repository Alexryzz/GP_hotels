package org.testwork.gp_hotels.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalTime;

@Embeddable
@Data
public class ArrivalTime {
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkIn;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkOut;
}
