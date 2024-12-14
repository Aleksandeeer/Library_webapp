package org.example.controllers;

import org.example.models.Book;
import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;;

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

    @PostMapping("/addBook")
    public String addBook(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "author") String author,
            @RequestParam(name = "genre") String genre,
            @RequestParam(name = "publicationYear") int publicationYear,
            @RequestParam(name = "ISBN") String ISBN,
            @RequestParam(name = "availabilityStatus") boolean availabilityStatus,
            Model model) {

        // Преобразуем строку genre в перечисление Genre
        Book.Genre bookGenre = Book.Genre.valueOf(genre.toUpperCase());

        // Создаем новый объект книги
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(bookGenre);
        book.setPublication_year(publicationYear);
        book.setISBN(ISBN);
        book.setAvailabilityStatus(availabilityStatus);

        // Сохраняем книгу в базе данных через сервис
        service.addBook(book);

        // Перенаправляем на страницу со списком книг или другую страницу
        return "redirect:/books";
    }
}
