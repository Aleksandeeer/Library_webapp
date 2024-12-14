package org.example.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

public class Person {
    @Getter @Setter String full_name;
    @Getter @Setter String email;
    @Getter @Setter String phone_number;
}
