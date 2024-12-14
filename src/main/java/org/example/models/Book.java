package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    @Getter @Setter String author;
    @Getter @Setter Genre genre;
    @Getter @Setter String title;
    @Getter @Setter int publication_year;
    /// ISBN
    /// 111-5-22222-333-4
    /// 111-prefix, 5-код страны, 22222-код издательства, 333-номер книги в издательстве, 4-контрольная цифра
    @Getter @Setter String ISBN;
    @Getter @Setter boolean availabilityStatus;

    public enum Genre {
        FANTASY, ACTION, COMEDY, DRAMA, DETECTIVE, NOVEL, SCIENCE, TUTORIAL
    }

    public Book(String author, String title, Genre genre, int publication_year, String ISBN, boolean availabilityStatus) {
        this.author = author;
        this.genre = genre;
        this.publication_year = publication_year;
        this.ISBN = ISBN;
        this.availabilityStatus = availabilityStatus;
        this.title = title;
    }

    public Book() {

    }

}
