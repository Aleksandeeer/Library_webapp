package org.example.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    Date registration_date;
    String login;
    boolean membership_status;
    String full_name;
    String email;
    String phone_number;
    String sha256_password;
    int role = 0; // ? 0 - Гость, 1 - Читатель, 2 - Работник
}
