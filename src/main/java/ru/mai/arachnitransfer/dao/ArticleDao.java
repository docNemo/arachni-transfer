package ru.mai.arachnitransfer.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.mai.arachnitransfer.domain.Article;
import ru.mai.arachnitransfer.utils.HibernateSessionFactoryUtil;

public class ArticleDao {
    public void save(Article article) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(article);
        tx1.commit();
        session.close();
    }
}
