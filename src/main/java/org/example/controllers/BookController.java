package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.models.Book;
import org.example.models.Library_worker;
import org.example.models.Member;
import org.example.models.Person;
import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class BookController {
//    String dateTimeString_ex = "2023-10-27 14:35:12";
    String pattern1 = "yyyy-MM-dd HH:mm:ss";
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
            @RequestParam(name = "URL_author") String URL_author,
            Model model) {

        // Преобразуем строку genre в перечисление Genre
        Book.Genre bookGenre = Book.Genre.valueOf(genre.toUpperCase());

        // Создаем новый объект книги
        Book book = new Book();
        book.setTitle(title);
        book.setURL(URL);
        book.setAuthor(author);
        book.setURL_author(URL_author);
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

    @PostMapping("/addLibraryWorker")
    public String add_lib_worker(Model model,
                                 @RequestParam(name = "full_name") String full_name,
                                 @RequestParam(name = "email") String email,
                                 @RequestParam(name = "phone_number") String phone_number,
                                 @RequestParam(name = "registration_date") String registration_date,
                                 @RequestParam(name = "login") String login,
                                 @RequestParam(name = "sha256_password") String sha256_password,
                                 @RequestParam(name = "membership_status") boolean membership_status) {
        Library_worker library_worker = new Library_worker();
        library_worker.setId(service.get_last_worker().getId() + 1);
        library_worker.setEmail(email);
        library_worker.setFull_name(full_name);
        library_worker.setRegistration_date(Date.valueOf(registration_date));
        library_worker.setSha256_password(service.hashing_password(sha256_password));
        library_worker.setPhone_number(phone_number);
        library_worker.setLogin(login);
        library_worker.setMembership_status(membership_status);

        service.addWorker(library_worker);

        return "redirect:/books";
    }
}
