package training.iqgateway.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.utils.HibernateUtils;

public class OffenceTypesDAOImpl implements OffenceTypesDAO {

    @Override
    public Integer insertOffenceTypes(OffenceTypesEO offTypesEO) {

        String SelectMaxIdFromOffenceTypesHQL =
            "SELECT MAX(offence_id) FROM OffenceTypesEO";
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        sessionRef.beginTransaction();
        Query queryRef =
            sessionRef.createQuery(SelectMaxIdFromOffenceTypesHQL);

        Number maxIdResult = (Number)queryRef.uniqueResult();
        int maxID = (maxIdResult != null) ? maxIdResult.intValue() : 0;

        offTypesEO.setOffence_id(maxID + 1);

        Integer returnedPK = (Integer)sessionRef.save(offTypesEO);
        sessionRef.getTransaction().commit();
        sessionRef.close();
        return returnedPK;
    }

    @Override
    public Integer updateOffenceTypes(OffenceTypesEO offTypesEO) {
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        sessionRef.beginTransaction();
        sessionRef.update(offTypesEO);
        sessionRef.getTransaction().commit();
        sessionRef.close();
        return 1;

    }

    @Override
    public Integer deleteOffenceTypes(Integer offenceID) {
        try {
            Session sessionRef =
                HibernateUtils.getSessionFactory().openSession();
            sessionRef.beginTransaction();
            OffenceTypesEO offTypesEO =
                (OffenceTypesEO)sessionRef.get(OffenceTypesEO.class,
                                               offenceID);
            if (offTypesEO != null) {
                sessionRef.delete(offTypesEO);
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
    public OffenceTypesEO findByOffenceID(Integer offenceID) {
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        OffenceTypesEO offTypesEO =
            (OffenceTypesEO)sessionRef.get(OffenceTypesEO.class, offenceID);
        sessionRef.close();
        return offTypesEO;
    }

    @Override
    public List<OffenceTypesEO> findAllOffenceTypes() {

        String SelectAllOffenceTypesHQL = "FROM OffenceTypesEO";
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        Query queryRef = sessionRef.createQuery(SelectAllOffenceTypesHQL);
        List<OffenceTypesEO> OffTypesEOList = queryRef.list();
        sessionRef.close();
        return OffTypesEOList;
    }

    @Override
    public List<OffenceTypesEO> findOffenceTypesByOffenceType(String offenceType) {

        String SelectVehOffenceByOffenceTypeHQL =
            "SELECT o FROM OffenceTypesEO AS o WHERE o.offence_type LIKE :offence_type";
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        Query queryRef = sessionRef.createQuery(SelectVehOffenceByOffenceTypeHQL);
        queryRef.setParameter("offence_type", offenceType);
        List<OffenceTypesEO> OffTypesEOList = queryRef.list();
        sessionRef.close();
        return OffTypesEOList;
    }

    @Override
    public List<OffenceTypesEO> findOffenceTypesByVehicleType(String givenvehicleType) {

        String SelectVehOffenceByVehTypeHQL =
            "SELECT o FROM OffenceTypesEO AS o WHERE o.vehicle_type = :vehicle_type";
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        Query queryRef = sessionRef.createQuery(SelectVehOffenceByVehTypeHQL);
        queryRef.setParameter("vehicle_type", givenvehicleType);
        List<OffenceTypesEO> OffTypesEOList = queryRef.list();
        sessionRef.close();
        return OffTypesEOList;
    }

    @Override
    public List<OffenceTypesEO> findOffenceTypesByPenalty(Integer givenpenaltyAmt) {

        String SelectVehOffenceByVehTypeHQL =
            "SELECT o FROM OffenceTypesEO AS o WHERE o.penalty_amt = :penalty_amt";
        Session sessionRef = HibernateUtils.getSessionFactory().openSession();
        Query queryRef = sessionRef.createQuery(SelectVehOffenceByVehTypeHQL);
        queryRef.setParameter("penalty_amt", givenpenaltyAmt);
        List<OffenceTypesEO> OffTypesEOList = queryRef.list();
        sessionRef.close();
        return OffTypesEOList;
    }

}
