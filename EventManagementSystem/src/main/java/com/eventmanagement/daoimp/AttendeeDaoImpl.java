package com.eventmanagement.daoimp;

import com.eventmanagement.dao.AttendeeDao;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AttendeeDaoImpl implements AttendeeDao {
    @Override
    public void addAttendee(Attendee a) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(a);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public Attendee getAttendee(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Attendee.class, id);
        }
    }

    @Override
    public List<Attendee> getAllAttendees() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Attendee", Attendee.class).list();
        }
    }

    @Override
    public void updateAttendee(Attendee a) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.merge(a);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAttendee(int id) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Attendee a = s.get(Attendee.class, id);
            if (a != null) s.remove(a);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }
}
