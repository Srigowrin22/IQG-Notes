package training.iqgateway.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import training.iqgateway.dao.RegistrationDAO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.utils.HibernateUtils;

public class RegistrationDAOImpl implements RegistrationDAO {

    @Override
    public Integer insertRegistration(RegistrationEO registrationEO) {
        try {
            Session sessionRef =
                HibernateUtils.getSessionFactory().openSession();
            sessionRef.beginTransaction();
            String returedPK = (String)sessionRef.save(registrationEO);
            System.out.println(returedPK);
            sessionRef.getTransaction().commit();
            sessionRef.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer updateRegistration(RegistrationEO registrationEO) {
        try {
            Session sessionRef =
                HibernateUtils.getSessionFactory().openSession();
            sessionRef.beginTransaction();
            sessionRef.update(registrationEO);
            sessionRef.getTransaction().commit();
            sessionRef.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer deleteRegistration(String regisID) {
        try {
            Session sessionRef =
                HibernateUtils.getSessionFactory().openSession();
            sessionRef.beginTransaction();
            RegistrationEO registrationEO =
                (RegistrationEO)sessionRef.get(RegistrationEO.class, regisID);
            if (registrationEO != null) {
                sessionRef.delete(registrationEO);
                sessionRef.getTransaction().commit();
                sessionRef.close();
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public RegistrationEO findRegistrationByID(String regisID) {
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        RegistrationEO registrationEO =
            (RegistrationEO)sessionRef.get(RegistrationEO.class, regisID);
        sessionRef.close();
        return registrationEO;
    }


    @Override
    public List<RegistrationEO> findRegistrationByAadhar(String aadhar) {

        String SelectRegisByAadharHQL =
            "SELECT r FROM RegistrationEO AS r WHERE r.owner_aadhar.owner_aadhar = :owner_aadhar";
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        Query queryRef = sessionRef.createQuery(SelectRegisByAadharHQL);
        queryRef.setParameter("owner_aadhar", aadhar);
        List<RegistrationEO> registrationEOList = queryRef.list();
        sessionRef.close();
        return registrationEOList;
    }

    @Override
    public List<RegistrationEO> findAllRegistrations() {

        String SelectAllRegisHQL = "FROM RegistrationEO";
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        Query queryRef = sessionRef.createQuery(SelectAllRegisHQL);
        List<RegistrationEO> registrationEOList = queryRef.list();
        sessionRef.close();
        return registrationEOList;
    }

    public RegistrationEO findRegistrationByVehID(Integer vehID) {
        String SelectRegisByAadharHQL =
            "SELECT r FROM RegistrationEO AS r WHERE r.vehicle_id = :vehicle_id";
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        Query queryRef = sessionRef.createQuery(SelectRegisByAadharHQL);
        queryRef.setParameter("vehicle_id", vehID);
        RegistrationEO registrationEO= (RegistrationEO) queryRef.uniqueResult();
        sessionRef.close();
        return registrationEO;
    }
}
