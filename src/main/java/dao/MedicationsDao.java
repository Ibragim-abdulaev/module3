package dao;

import models.Medications;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;
import java.util.List;

public class MedicationsDao {
    public Medications saveMedication(Medications medication) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(medication);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return medication;
    }

    public void updateMedications(Medications medication) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(medication);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Medications getMedicationsById(int id) {
        Transaction transaction = null;
        Medications medication = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            medication = session.get(Medications.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return medication;
    }


    public List<Medications> getAllMedications() {
        List<Medications> medications = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            medications = session.createQuery("from models.Medications", Medications.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medications;
    }

    public void deleteMedications(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Medications medications = session.get(Medications.class, id);
            if (medications != null) {
                session.delete(medications);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
