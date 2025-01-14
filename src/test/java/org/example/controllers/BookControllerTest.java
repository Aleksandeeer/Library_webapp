package org.example.controllers;

import org.example.models.Book;
import org.example.services.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Test
    void showBooksTest() {
        // Mock сервис и модель
        Service mockService = Mockito.mock(Service.class);
        Model mockModel = Mockito.mock(Model.class);

        // Создаем данные для теста
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());

        when(mockService.getBooks()).thenReturn(books);
        when(mockService.getRole()).thenReturn(1);

        // Тестируем контроллер
        BookController controller = new BookController();
        String viewName = controller.showBooks(mockModel);

        // Проверяем результат
        assertEquals("books", viewName);
        verify(mockModel).addAttribute("role", 1);
        verify(mockModel).addAttribute("books", books);
    }
}

