package org.example.DAO;

import org.example.Domain.Manufacture;
import org.example.Domain.Phone;
import org.example.Utils.Services;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class PhoneDAO implements Services<Phone> {
    Session session;


    public PhoneDAO() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Phone.class).addAnnotatedClass(Manufacture.class).buildSessionFactory();
        this.session = sessionFactory.getCurrentSession();
    }


    @Override
    public boolean add(Phone p) {
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
    public Phone get(String id) {
        try {
            session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            if (phone == null) {
                return null;
            }
            session.getTransaction().commit();
            session.close();
            return phone;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Phone> getAll() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Phone");
            List<Phone> phones = query.list();
            session.getTransaction().commit();
            session.close();
            return phones;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(String id) {
        try {
            session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            session.delete(phone);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Phone p) {
        try {
            session.beginTransaction();
            Phone phone = session.get(Phone.class, p.getID());
            session.delete(phone);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Phone p) {
        try {
            session.beginTransaction();
            Phone phone = session.get(Phone.class, p.getID());
            if(phone == null) {
                return false;
            }
            phone.setName(p.Name);
            phone.setColor(p.Color);
            phone.setCountry(p.Country);
            phone.setPrice(p.Price);
            phone.setManufacture(p.manufacture);
            phone.setQuantity(p.Quantity);
            session.update(phone);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Phone getSellingHighestPrice() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Phone P ORDER BY P.Price DESC");
            List<Phone> phones = query.list();
            session.getTransaction().commit();
            session.close();
            return phones.get(0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Phone> getPhoneSorted() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Phone P ORDER BY P.Country ASC," +
                    " P.Price DESC");
            List<Phone> phones = query.list();
            session.getTransaction().commit();
            session.close();
            return phones;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Phone> getPhoneHaveFiftyMillionVND() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Phone P where P.Price >= " +
                    "50000000");
            List<Phone> phones = query.list();
            session.getTransaction().commit();
            session.close();
            return phones;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Phone getPhoneHavePinkColorAndCostOver15Million() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Phone P where P.Color like " +
                    "'pink' and P.Price >= 15000000");
            List<Phone> phones = query.list();
            if (phones.isEmpty()) {
                return null;
            }
            session.getTransaction().commit();
            session.close();
            return phones.get(0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
