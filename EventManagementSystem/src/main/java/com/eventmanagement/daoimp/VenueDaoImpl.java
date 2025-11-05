package com.eventmanagement.daoimp;

import com.eventmanagement.dao.VenueDao;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VenueDaoImpl implements VenueDao {
    @Override
    public void addVenue(Venue v) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(v);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public Venue getVenue(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Venue.class, id);
        }
    }

    @Override
    public List<Venue> getAllVenues() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Venue", Venue.class).list();
        }
    }

    @Override
    public void updateVenue(Venue v) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.merge(v);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteVenue(int id) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Venue v = s.get(Venue.class, id);
            if (v != null) s.remove(v);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }
}
