package org.example.services;

import lombok.Getter;
import org.example.models.Book;
import org.example.models.Library_worker;
import org.example.models.Loan;
import org.example.models.Member;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Getter List<Book> books;
    @Getter List<Member> members;
    @Getter List<Library_worker> library_workers;
    @Getter List<Loan> loans;

    Session session;

    public Service() {
        // Открытие сессии
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            // Попытка выгрузки данных из таблицы
            books = session.createQuery("FROM Book", Book.class).list();
            members = session.createQuery("FROM Member", Member.class).list();
            library_workers = session.createQuery("FROM Library_worker", Library_worker.class).list();
            loans = session.createQuery("FROM Loan", Loan.class).list();
        } catch (Exception e) {
            System.out.println("Ошибка при обработке данных таблицы.");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
