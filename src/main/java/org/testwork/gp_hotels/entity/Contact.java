package org.testwork.gp_hotels.entity;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Contact {
    private String phone;
    private String email;
}
