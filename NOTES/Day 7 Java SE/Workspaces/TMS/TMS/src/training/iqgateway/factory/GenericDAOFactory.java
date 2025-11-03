package training.iqgateway.factory;

import training.iqgateway.dao.AdminDAO;
import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.dao.OwnerDAO;
import training.iqgateway.dao.RegistrationDAO;
import training.iqgateway.dao.RoleDAO;
import training.iqgateway.dao.VehicleDAO;
import training.iqgateway.dao.VehicleOffenceDAO;
import training.iqgateway.dao.impl.AdminDAOImpl;
import training.iqgateway.dao.impl.OffenceTypesDAOImpl;
import training.iqgateway.dao.impl.OwnerDAOImpl;
import training.iqgateway.dao.impl.RegistrationDAOImpl;
import training.iqgateway.dao.impl.RoleDAOImpl;
import training.iqgateway.dao.impl.VehicleDAOImpl;
import training.iqgateway.dao.impl.VehicleOffenceDAOImpl;

public class GenericDAOFactory {
    
    private static AdminDAO AdminDAORef;
    private static RoleDAO roleDAORef;
    private static OwnerDAO ownerDAORef;
    private static VehicleDAO vehicleDAORef;
    private static OffenceTypesDAO offenceTypesDAORef;
    private static RegistrationDAO registrationDAORef;
    private static VehicleOffenceDAO vehicleOffenceDAORef;
    
    public static AdminDAO createAdminDAO(){
        AdminDAORef = new AdminDAOImpl();
        return AdminDAORef;
    }
    
    public static RoleDAO createRoleDAO() {
            if (roleDAORef == null) {
                roleDAORef = new RoleDAOImpl();
            }
            return roleDAORef;
        }
        
        public static OwnerDAO createOwnerDAO() {
            if (ownerDAORef == null) {
                ownerDAORef = new OwnerDAOImpl();
            }
            return ownerDAORef;
        }
        
        public static VehicleDAO createVehicleDAO() {
            if (vehicleDAORef == null) {
                vehicleDAORef = new VehicleDAOImpl();
            }
            return vehicleDAORef;
        }
        
        public static OffenceTypesDAO createOffenceTypesDAO() {
            if (offenceTypesDAORef == null) {
                offenceTypesDAORef = new OffenceTypesDAOImpl();
            }
            return offenceTypesDAORef;
        }
        
        public static RegistrationDAO createRegistrationDAO() {
            if (registrationDAORef == null) {
                registrationDAORef = new RegistrationDAOImpl();
            }
            return registrationDAORef;
        }
        
        public static VehicleOffenceDAO createVehicleOffenceDAO() {
            if (vehicleOffenceDAORef == null) {
                vehicleOffenceDAORef = new VehicleOffenceDAOImpl();
            }
            return vehicleOffenceDAORef;
        }
}
