package ru.mai.arachnitransfer.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.mai.arachnitransfer.domain.Article;
import ru.mai.arachnitransfer.domain.Category;
import ru.mai.arachnitransfer.domain.Creator;
import ru.mai.arachnitransfer.utils.HibernateSessionFactoryUtil;

public class CreatorDao {
    public void save(Creator creator) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(creator);
        tx1.commit();
        session.close();
    }

    public Creator getByName(String name) {
        return (Creator) HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()
                .createQuery("SELECT FROM creators WHERE creators=" + name);
    }
}
