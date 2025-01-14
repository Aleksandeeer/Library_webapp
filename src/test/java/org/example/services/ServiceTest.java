package org.example.services;

import org.example.models.Book;
import org.example.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service();
    }

    @Test
    void addBookTest() {
        Book book = new Book();
        book.setTitle("Test Book");

        int initialSize = service.getBooks().size();
        service.addBook(book);

        assertEquals(initialSize + 1, service.getBooks().size());
        assertEquals("Test Book", service.getBooks().get(initialSize).getTitle());
    }

    @Test
    void isUserValidTest() {
        Member member = new Member();
        member.setLogin("test");
        member.setSha256_password(service.hashing_password("password"));

        service.registration_new_member(member);

        assertTrue(service.isUserValid(member));
    }
}
