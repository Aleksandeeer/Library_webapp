package org.example.controllers;

import org.example.models.Book;
import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;;

import java.util.List;

@Controller
public class BookController {
    private final Service service;

    public BookController() {
        this.service = new Service(); // Создание сервиса
    }

    // TODO: пока перенаправление на книги, потом исправить
    // Главная страница, которая будет перенаправлять на /books
    @GetMapping("/")
    public String redirectToBooks() {
        return "redirect:/books";
    }

    // Страница с книгами
    @GetMapping("/books")
    public String showBooks(Model model) {
        List<Book> books = service.getBooks();
        model.addAttribute("books", books);
        return "books";
    }
}
