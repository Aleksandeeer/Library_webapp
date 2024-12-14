package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Library_worker extends Person {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    @Getter @Setter String role;

    public Library_worker() {

    }
}
