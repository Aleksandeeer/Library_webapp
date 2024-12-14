package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Member extends Person {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    @Getter @Setter Date registration_date;
    @Getter @Setter boolean membership_status;

    public Member(){

    }
}
