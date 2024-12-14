package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// ? Выдача книг
@Entity
public class Loan {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    @Getter @Setter int book_id;
    @Getter @Setter int member_id;
    @Getter @Setter Date issue_date; // * Дата выдачи
    @Getter @Setter Date return_date; // * Дата возврата
    @Getter @Setter Status status;

    public enum Status {
        AT_THE_USER, // * У пользователя
        IN_LIBRARY, // * В библиотеке
        WRITTEN_OFF // * Списано (испорчена, запрещена и т.п.)
    }

    public Loan(){

    }
}
