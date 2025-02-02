package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.models.Book;
import org.example.models.Member;
import org.example.models.Person;
import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    private final Service service;

    public BookController() {
        this.service = new Service(); // Создание сервиса
    }

    // Главная страница, которая будет перенаправлять на /login
    @GetMapping("/")
    public String Main_page_function(Model model, HttpServletRequest request) {
        return "login";
    }

    // Страница с книгами
    @GetMapping("/books")
    public String showBooks(Model model) {
        List<Book> books = service.getBooks();
        // TODO: сделать проверку на роль в кнопке "добавить книгу"
        model.addAttribute("role", service.getRole());
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
            @RequestParam(name = "URL") String URL,
            Model model) {

        // Преобразуем строку genre в перечисление Genre
        Book.Genre bookGenre = Book.Genre.valueOf(genre.toUpperCase());

        // Создаем новый объект книги
        Book book = new Book();
        book.setTitle(title);
        book.setURL(URL);
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

    @PostMapping("/login")
    // TODO: не упаковывается в Person
    public String login(Model model, Person person) {
        if (service.isUserValid(person)) {
            // ? 0 - Гость, 1 - Читатель, 2 - Работник
            System.out.println("ROLE: " + service.getRole());
            person.setRole(service.getRole());

            model.addAttribute("role", service.getRole());
            model.addAttribute("person", person);
            model.addAttribute("books", service.getBooks());

            return "redirect:/books";
        } else {
            return "login";
        }
    }

    @PostMapping("/guest")
    public String guest(Model model) {
        service.setRole(0); // ? Гость
        return "redirect:/books";
    }

    @PostMapping("/registration")
    public String registration_page() {
        return "registration";
    }

    @PostMapping("/register")
    public String registration(Model model, Member member) {
        service.registration_new_member(member);
        member = service.get_last_member();
        return login(model, member);
    }
}
