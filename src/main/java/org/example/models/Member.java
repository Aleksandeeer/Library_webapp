package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Member extends Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    Date registration_date;
    String login;
    boolean membership_status;
    String full_name;
    String email;
    String phone_number;
    String sha256_password;

    public Member(){

    }
}
