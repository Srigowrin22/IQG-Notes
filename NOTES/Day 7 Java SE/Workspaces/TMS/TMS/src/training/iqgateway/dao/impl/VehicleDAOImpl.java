package training.iqgateway.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.VehicleDAO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.utils.DBUtils;

/**
 * DAO implementation for vehicle operations.
 */
public class VehicleDAOImpl implements VehicleDAO {

    private String SQLInsertStatement =
        "INSERT INTO VEHICLE VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private String SQLUpdateStatement =
        "UPDATE VEHICLE SET VEHICLE_BRAND = ?, VEHICLE_MODEL = ?, VEHICLE_TYPE = ?, FUEL_TYPE = ?, NO_OF_EXHAUST = ?, COLOR = ?, MANUFACTURE_DATE = ? WHERE VEHICLE_ID = ?";

    private String SQLDeleteStatement =
        "DELETE FROM VEHICLE WHERE VEHICLE_ID = ?";

    private String SQLSelectStatementByID =
        "SELECT * FROM VEHICLE WHERE VEHICLE_ID = ?";

    private String SQLSelectAllStatement =
        "SELECT * FROM VEHICLE";

    private String SQLSelectStatementByType =
        "SELECT * FROM VEHICLE WHERE VEHICLE_TYPE LIKE ?";

    /**
     * Inserts a new vehicle into the database.
     */
    public int insertVehicle(VehicleEO vehicleEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;

        try {
            pstat = con.prepareStatement(SQLInsertStatement);
            pstat.setInt(1, vehicleEO.getVehicleID());
            pstat.setString(2, vehicleEO.getVehicleBrand());
            pstat.setString(3, vehicleEO.getVehicleModel());
            pstat.setString(4, vehicleEO.getVehicleType());
            pstat.setString(5, vehicleEO.getFuelType());
            if (vehicleEO.getNoOfExhaust() != null) {
                pstat.setInt(6, vehicleEO.getNoOfExhaust());
            } else {
                pstat.setNull(6, java.sql.Types.INTEGER);
            }
            pstat.setString(7, vehicleEO.getColor());
            pstat.setDate(8, vehicleEO.getManufactureDate());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Updates a vehicle's details.
     */
    public int updateVehicle(VehicleEO vehicleEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;

        try {
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setString(1, vehicleEO.getVehicleBrand());
            pstat.setString(2, vehicleEO.getVehicleModel());
            pstat.setString(3, vehicleEO.getVehicleType());
            pstat.setString(4, vehicleEO.getFuelType());
            if (vehicleEO.getNoOfExhaust() != null) {
                pstat.setInt(5, vehicleEO.getNoOfExhaust());
            } else {
                pstat.setNull(5, java.sql.Types.INTEGER);
            }
            pstat.setString(6, vehicleEO.getColor());
            pstat.setDate(7, vehicleEO.getManufactureDate());
            pstat.setInt(8, vehicleEO.getVehicleID());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Deletes a vehicle by its ID.
     */
    public int deleteVehicle(Integer vehicleID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;

        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setInt(1, vehicleID);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    public VehicleEO findVehicleByID(Integer vehicleID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        VehicleEO vehicleEO = null;

        try {
            pstat = con.prepareStatement(SQLSelectStatementByID);
            pstat.setInt(1, vehicleID);
            rs = pstat.executeQuery();

            while (rs.next()) {
                vehicleEO = new VehicleEO();
                vehicleEO.setVehicleID(rs.getInt("VEHICLE_ID"));
                vehicleEO.setVehicleBrand(rs.getString("VEHICLE_BRAND"));
                vehicleEO.setVehicleModel(rs.getString("VEHICLE_MODEL"));
                vehicleEO.setVehicleType(rs.getString("VEHICLE_TYPE"));
                vehicleEO.setFuelType(rs.getString("FUEL_TYPE"));
                vehicleEO.setNoOfExhaust(rs.getInt("NO_OF_EXHAUST"));
                if (rs.wasNull()) vehicleEO.setNoOfExhaust(null);
                vehicleEO.setColor(rs.getString("COLOR"));
                vehicleEO.setManufactureDate(rs.getDate("MANUFACTURE_DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return vehicleEO;
    }

    public List<VehicleEO> findAllVehicles() {
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;
        List<VehicleEO> vehicleList = new ArrayList<VehicleEO>();
        VehicleEO vehicleEO = null;

        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);

            while (rs.next()) {
                vehicleEO = new VehicleEO();
                vehicleEO.setVehicleID(rs.getInt("VEHICLE_ID"));
                vehicleEO.setVehicleBrand(rs.getString("VEHICLE_BRAND"));
                vehicleEO.setVehicleModel(rs.getString("VEHICLE_MODEL"));
                vehicleEO.setVehicleType(rs.getString("VEHICLE_TYPE"));
                vehicleEO.setFuelType(rs.getString("FUEL_TYPE"));
                vehicleEO.setNoOfExhaust(rs.getInt("NO_OF_EXHAUST"));
                if (rs.wasNull()) vehicleEO.setNoOfExhaust(null);
                vehicleEO.setColor(rs.getString("COLOR"));
                vehicleEO.setManufactureDate(rs.getDate("MANUFACTURE_DATE"));
                vehicleList.add(vehicleEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(stat);
        DBUtils.close(con);
        return vehicleList;
    }

    public List<VehicleEO> findVehicleByType(String vehicleType) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<VehicleEO> vehicleList = new ArrayList<VehicleEO>();
        VehicleEO vehicleEO = null;

        try {
            pstat = con.prepareStatement(SQLSelectStatementByType);
            pstat.setString(1, vehicleType);
            rs = pstat.executeQuery();

            while (rs.next()) {
                vehicleEO = new VehicleEO();
                vehicleEO.setVehicleID(rs.getInt("VEHICLE_ID"));
                vehicleEO.setVehicleBrand(rs.getString("VEHICLE_BRAND"));
                vehicleEO.setVehicleModel(rs.getString("VEHICLE_MODEL"));
                vehicleEO.setVehicleType(rs.getString("VEHICLE_TYPE"));
                vehicleEO.setFuelType(rs.getString("FUEL_TYPE"));
                vehicleEO.setNoOfExhaust(rs.getInt("NO_OF_EXHAUST"));
                if (rs.wasNull()) vehicleEO.setNoOfExhaust(null);
                vehicleEO.setColor(rs.getString("COLOR"));
                vehicleEO.setManufactureDate(rs.getDate("MANUFACTURE_DATE"));
                vehicleList.add(vehicleEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return vehicleList;
    }
}
