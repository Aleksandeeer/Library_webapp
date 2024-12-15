package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    String full_name;
    String email;
    String phone_number;
    String sha256_password;
}
