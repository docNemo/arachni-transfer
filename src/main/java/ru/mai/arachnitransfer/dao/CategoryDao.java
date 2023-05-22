package ru.mai.arachnitransfer.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.mai.arachnitransfer.domain.Article;
import ru.mai.arachnitransfer.domain.Category;
import ru.mai.arachnitransfer.utils.HibernateSessionFactoryUtil;

public class CategoryDao {
    public void save(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(category);
        tx1.commit();
        session.close();
    }

    public Category getByName(String name) {
        return (Category) HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()
                .createQuery("SELECT FROM categories WHERE category=" + name);
    }
}
