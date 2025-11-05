package com.eventmanagement.daoimp;

import com.eventmanagement.dao.OrganizerDao;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrganizerDaoImpl implements OrganizerDao {
    @Override
    public void addOrganizer(Organizer o) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(o);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public Organizer getOrganizer(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Organizer.class, id);
        }
    }

    @Override
    public List<Organizer> getAllOrganizers() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Organizer", Organizer.class).list();
        }
    }

    @Override
    public void updateOrganizer(Organizer o) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.merge(o);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteOrganizer(int id) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Organizer o = s.get(Organizer.class, id);
            if (o != null) s.remove(o);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }
}
