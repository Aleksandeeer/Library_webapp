package org.example.services;

import lombok.Getter;
import org.example.models.Book;
import org.example.models.Library_worker;
import org.example.models.Loan;
import org.example.models.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Getter
public class Service {
    List<Book> books;
    List<Member> members;
    List<Library_worker> library_workers;
    List<Loan> loans;

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

    public void addBook(Book book) {
        books.add(book);

        // Открытие сессии
        session = HibernateUtil.getSessionFactory().openSession();

        // Транзакция для сохранения объекта
        Transaction transaction = null;

        try {
            // Начинаем транзакцию
            transaction = session.beginTransaction();

            // Сохранение книги в базе
            session.save(book);

            // Подтверждаем транзакцию
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                // Если ошибка, откатываем транзакцию
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Закрываем сессию
            session.close();
        }
    }
}
