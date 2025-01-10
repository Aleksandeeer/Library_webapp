package org.example.services;

import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.Setter;
import org.example.components.HibernateUtil;
import org.example.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.nio.charset.StandardCharsets;
import java.util.List;

@org.springframework.stereotype.Service
@Getter
public class Service {
    List<Book> books;
    List<Member> members;
    List<Library_worker> library_workers;
    List<Loan> loans;
    @Getter @Setter
    int role = 0; // ? 0 - Гость, 1 - Читатель, 2 - Работник

    Session session;

    public Service() {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            // Выгрузки данных из таблиц
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

        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean isUserValid(Person person) {
        // ? SHA-256 encryption
        System.out.println("LOGIN:" + person.getLogin() + "\tPASSWORD:" + person.getSha256_password());
        person.setSha256_password(hashing_password(person.getSha256_password()));
        System.out.println("SHA256: " + person.getSha256_password());

        for (Member member : members) {
            if (member.getLogin().equals(person.getLogin()) &&
                    member.getSha256_password().equals(person.getSha256_password())) {
                role = 1; // ? Читатель
                System.out.println("ЧИТАТЕЛЬ");
                return true;
            }
        }

        for (Library_worker library_worker : library_workers) {
            if (library_worker.getLogin().equals(person.getLogin()) &&
                    library_worker.getSha256_password().equals(person.getSha256_password())) {
                role = 2; // ? Работник
                System.out.println("РАБОТНИК");
                return true;
            }
        }

        System.out.println("ФИАСКО ЛОГИН");
        return false;
    }

    public void registration_new_member(Member member) {
        member.setSha256_password(hashing_password(member.getSha256_password()));
        member.setRole(1);

        members.add(member);

        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public String hashing_password(String unhashing_password) {
        return Hashing.sha256()
                .hashString(unhashing_password, StandardCharsets.UTF_8)
                .toString();
    }

}
