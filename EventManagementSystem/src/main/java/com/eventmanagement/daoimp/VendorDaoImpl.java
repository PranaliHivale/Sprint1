package com.eventmanagement.daoimp;

import com.eventmanagement.dao.VendorDao;
import com.eventmanagement.entity.Vendor;
import com.eventmanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VendorDaoImpl implements VendorDao {
    @Override
    public void addVendor(Vendor v) {
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
    public Vendor getVendor(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Vendor.class, id);
        }
    }

    @Override
    public List<Vendor> getAllVendors() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Vendor", Vendor.class).list();
        }
    }

    @Override
    public void updateVendor(Vendor v) {
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
    public void deleteVendor(int id) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Vendor v = s.get(Vendor.class, id);
            if (v != null) s.remove(v);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
    }
}
