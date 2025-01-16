package org.example.controllers;

import com.google.common.hash.Hashing;
import org.example.components.HibernateUtil;
import org.example.interfaces.MemberRepository;
import org.example.models.Book;
import org.example.models.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private Session session;

    public MemberRepositoryImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Member> getAllMembers() {
        return session.createQuery("FROM Member", Member.class).list();
    }

    @Override
    public void addMember(Member member) {
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
}