package training.iqgateway.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import training.iqgateway.DAO.DepartmentDAO;
import training.iqgateway.entity.DepartmentEO;
 
 
public class DepartmentDAOImpl implements DepartmentDAO {
 
	private SessionFactory sessionFactoryRef;
	public DepartmentDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	public DepartmentDAOImpl(SessionFactory sessionFactoryRef) {
		super();
		this.sessionFactoryRef = sessionFactoryRef;
	}
 
	public SessionFactory getSessionFactoryRef() {
		return sessionFactoryRef;
	}
 
	public void setSessionFactoryRef(SessionFactory sessionFactoryRef) {
		this.sessionFactoryRef = sessionFactoryRef;
	}
 
 
	@Override
	public Integer insertDepartment(DepartmentEO deptEORef) {
		// TODO Auto-generated method stub
		Session sessionRef = sessionFactoryRef.openSession();
			sessionRef.beginTransaction();
				Integer returnedPk = (Integer) sessionRef.save(deptEORef);
			sessionRef.getTransaction().commit();
		sessionRef.close();
		return returnedPk;
	}
 
	@Override
	public void updateDepartment(DepartmentEO deptEORef) {
		// TODO Auto-generated method stub
		Session sessionRef = sessionFactoryRef.openSession();
		sessionRef.beginTransaction();
			sessionRef.update(deptEORef);
		sessionRef.getTransaction().commit();
		sessionRef.close();
	}
 
	@Override
	public void deleteDepartment(Integer deptId) {
		// TODO Auto-generated method stub
		Session sessionRef = sessionFactoryRef.openSession();
		sessionRef.beginTransaction();
			DepartmentEO returnedDeptEO = 
					sessionRef.get(DepartmentEO.class, deptId);
			sessionRef.delete(returnedDeptEO);
		sessionRef.getTransaction().commit();
	sessionRef.close();
	}
 
	@Override
	public DepartmentEO findDepartmentByID(Integer deptId) {
		// TODO Auto-generated method stub
		Session sessionRef = sessionFactoryRef.openSession();
			DepartmentEO returnedDeptEO = 
					sessionRef.get(DepartmentEO.class, deptId);
		sessionRef.close();
		return returnedDeptEO;
	}
 
	@Override
	public List<DepartmentEO> findAll() {
		// TODO Auto-generated method stub
		Session sessionRef = sessionFactoryRef.openSession();
			Query queryRef = sessionRef.createNamedQuery("DepartmentEO.findAll");
			List<DepartmentEO> resultList = queryRef.list();
		sessionRef.close();
		return resultList;
	}
 
	@Override
	public List<DepartmentEO> findAllDepartmentsByLocation(Integer LocationId) {
		// TODO Auto-generated method stub
		Session sessionRef = sessionFactoryRef.openSession();
			Query queryRef = sessionRef.createNamedQuery("DepartmentEO.findByDeptLocation");
				queryRef.setParameter("givenLocationId", LocationId);
			List<DepartmentEO> resultList = queryRef.list();
		sessionRef.close();
	return resultList;
	}
 
}