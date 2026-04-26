package org.testwork.gp_hotels.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Embeddable
@Data
@Schema(description = "Arrival time information")
public class ArrivalTime {
    @Schema(example = "14:00", description = "Check-in time (HH:mm)")
    @NotNull(message = "Registration time is required")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkIn;
    @Schema(example = "12:00", description = "Check-out time (HH:mm)")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkOut;
}
