package com.eventmanagement.daoimp;

import com.eventmanagement.dao.EventDao;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Vendor;
import com.eventmanagement.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EventDaoImpl implements EventDao {
    @Override
    public void addEvent(Event e) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(e);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public Event getEvent(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Event e = s.get(Event.class, id);
            if (e != null) {
                // initialize collections if needed
                Hibernate.initialize(e.getAttendees());
                Hibernate.initialize(e.getVendors());
            }
            return e;
        }
    }

    @Override
    public List<Event> getAllEvents() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Event", Event.class).list();
        }
    }

    @Override
    public void updateEvent(Event e) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.merge(e);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEvent(int id) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Event e = s.get(Event.class, id);
            if (e != null) s.remove(e);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void addAttendeeToEvent(int eventId, Attendee a) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Event e = s.get(Event.class, eventId);
            if (e != null) {
                // if attendee is detached, merge it
                Attendee managed = s.get(Attendee.class, a.getAttId());
                if (managed == null) s.persist(a);
                e.getAttendees().add(a);
                s.merge(e);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void removeAttendeeFromEvent(int eventId, int attendeeId) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Event e = s.get(Event.class, eventId);
            Attendee a = s.get(Attendee.class, attendeeId);
            if (e != null && a != null) {
                e.getAttendees().removeIf(att -> att.getAttId() == attendeeId);
                s.merge(e);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void addVendorToEvent(int eventId, Vendor v) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Event e = s.get(Event.class, eventId);
            if (e != null) {
                Vendor managed = s.get(Vendor.class, v.getVendId());
                if (managed == null) s.persist(v);
                e.getVendors().add(v);
                s.merge(e);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void removeVendorFromEvent(int eventId, int vendorId) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Event e = s.get(Event.class, eventId);
            Vendor v = s.get(Vendor.class, vendorId);
            if (e != null && v != null) {
                e.getVendors().removeIf(ven -> ven.getVendId() == vendorId);
                s.merge(e);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }
}
