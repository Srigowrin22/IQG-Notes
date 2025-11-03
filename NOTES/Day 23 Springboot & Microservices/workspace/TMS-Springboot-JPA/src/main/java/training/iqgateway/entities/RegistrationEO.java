package training.iqgateway.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "REGISTRATION")
public class RegistrationEO {

	@Id
	@Column(name = "REGISTRATION_ID", length = 50)
	private String registrationId;

	@Column(name = "LOCATION", length = 100, nullable = false)
	private String location;

	@Temporal(TemporalType.DATE)
	@Column(name = "REGISTRATION_DATE")
	private Date registrationDate;

	@Column(name = "REGISTRAR", length = 20)
	private String registrar;

	// Many registrations belong to one vehicle
	@ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "vehicle-registrations")
	@JoinColumn(name = "VEHICLE_ID", nullable = false)
	private VehicleEO vehicle;

	// Many registrations belong to one owner (by owner aadhar)
	@ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "owner-registrations")
	@JoinColumn(name = "OWNER_AADHAR", referencedColumnName = "OWNER_AADHAR", nullable = false)
	private OwnerEO owner;

	// One registration can have many vehicle offences
	@OneToMany(mappedBy = "registration")
    @JsonManagedReference(value = "registration-vehicleOffences")
	private Set<VehicleOffenceEO> vehicleOffences;

	// Constructors, getters, setters

	public RegistrationEO() {
		this.registrationDate = new Date();
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrar() {
		return registrar;
	}

	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}

	public VehicleEO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleEO vehicle) {
		this.vehicle = vehicle;
	}

	public OwnerEO getOwner() {
		return owner;
	}

	public void setOwner(OwnerEO owner) {
		this.owner = owner;
	}

	public Set<VehicleOffenceEO> getVehicleOffences() {
		return vehicleOffences;
	}

	public void setVehicleOffences(Set<VehicleOffenceEO> vehicleOffences) {
		this.vehicleOffences = vehicleOffences;
	}
}
