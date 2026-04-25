package org.testwork.gp_hotels.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Embeddable
@Data
public class ArrivalTime {
    @NotNull(message = "Registration time is required")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkIn;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkOut;
}
