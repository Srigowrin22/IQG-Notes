package training.iqgateway.tests;

import org.junit.Assert;
import org.junit.Test;

import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.dao.impl.OffenceTypesDAOImpl;
import training.iqgateway.entities.OffenceTypesEO;

public class OffenceTypesEOTest {

	private OffenceTypesDAO offenceDAORef = new OffenceTypesDAOImpl();

	@Test
	public void testInsertOffenceTypes() {

		OffenceTypesEO offTypesEO = new OffenceTypesEO(12, "Rashy Drive", "CAR", 5270);

		Integer returnedPK = offenceDAORef.insertOffenceTypes(offTypesEO);

		Assert.assertEquals(new Integer(12), returnedPK);
	}

	@Test
	public void testUpdateOffenceTypes() {

		OffenceTypesEO offTypesEO = new OffenceTypesEO(12, "Rash Driving", "CAR", 6500);

		offenceDAORef.updateOffenceTypes(offTypesEO);

	}

	@Test
	public void testFindByOffenceID() {

		OffenceTypesEO offTypeEO = offenceDAORef.findByOffenceID(12);

		Assert.assertNotNull(offTypeEO);

		System.out.println(offTypeEO);

	}

	@Test
	public void testDeleteOffenceType() {
		
		offenceDAORef.deleteOffenceTypes(12);
	}
}
