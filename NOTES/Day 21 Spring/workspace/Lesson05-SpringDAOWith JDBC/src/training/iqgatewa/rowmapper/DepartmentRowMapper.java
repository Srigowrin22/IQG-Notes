package training.iqgatewa.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import org.springframework.jdbc.core.RowMapper;
 
import training.iqgateway.entities.DepartmentEO;
 
public class DepartmentRowMapper implements RowMapper<DepartmentEO> {
 
	@Override
	public DepartmentEO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		DepartmentEO departmentEORef = new DepartmentEO();
		departmentEORef.setDepartmentID(rs.getInt("DEPARTMENT_ID"));
		departmentEORef.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
		departmentEORef.setManagerID(rs.getInt("MANAGER_ID"));
		departmentEORef.setLocationID(rs.getInt("LOCATION_ID"));
		return departmentEORef;
	}
 
}