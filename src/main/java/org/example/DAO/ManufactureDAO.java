package org.example.DAO;

import org.example.Domain.Manufacture;
import org.example.Domain.Phone;
import org.example.Utils.Services;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ManufactureDAO implements Services<Manufacture> {
    Session session;

    public ManufactureDAO() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Phone.class).addAnnotatedClass(Manufacture.class).buildSessionFactory();
        this.session = sessionFactory.getCurrentSession();
    }
    @Override
    public boolean add(Manufacture p) {
        try {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Manufacture get(String id) {
        try {
            session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture == null) {
                return null;
            }
            session.getTransaction().commit();
            session.close();
            return manufacture;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Manufacture> getAll() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Manufacture");
            List<Manufacture> manufactures = query.list();
            session.getTransaction().commit();
            session.close();
            return manufactures;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(String id) {
        try {
            session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            session.delete(manufacture);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Manufacture p) {
        try {
            session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, p.getID());
            session.delete(manufacture);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(Manufacture p) {
        try {
            session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, p.getID());
            if (manufacture == null) {
                return false;
            }
            manufacture.setName(p.Name);
            manufacture.setLocation(p.Location);
            manufacture.setEmployee(p.Employee);
            session.update(manufacture);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAllMoreThan100Employees() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Manufacture m where m.Employee " +
                    " <= 100");
            List<Manufacture> manufactures = query.list();
            session.getTransaction().commit();
            session.close();
            if (manufactures.size() > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long sumAllEmployees() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT SUM(m.Employee) FROM Manufacture m");
            List<Long> result = query.list();
            session.getTransaction().commit();
            session.close();
            return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
