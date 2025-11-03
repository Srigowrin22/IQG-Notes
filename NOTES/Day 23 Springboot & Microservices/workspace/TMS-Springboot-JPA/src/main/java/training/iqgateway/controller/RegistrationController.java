package training.iqgateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import training.iqgateway.dto.RegistrationDTO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/all")
	public ResponseEntity<List<RegistrationDTO>> getAllRegistrations() {
		List<RegistrationDTO> registrations = registrationService.findAllRegistrations();
		return ResponseEntity.ok(registrations);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RegistrationDTO> getRegistrationById(@PathVariable String id) {
		RegistrationDTO registration = registrationService.findRegistrationByID(id);
		if (registration != null) {
			return ResponseEntity.ok(registration);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/vehicle/{vehId}")
	public ResponseEntity<RegistrationDTO> getRegistrationByVehicleId(@PathVariable Integer vehId) {
		RegistrationDTO registration = registrationService.findRegistrationByVehID(vehId);
		if (registration != null) {
			return ResponseEntity.ok(registration);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/owner/{aadhar}")
	public ResponseEntity<List<RegistrationDTO>> getRegistrationsByOwnerAadhar(@PathVariable String aadhar) {
		List<RegistrationDTO> registrations = registrationService.findRegistrationByAadhar(aadhar);
		return ResponseEntity.ok(registrations);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addRegistration(@RequestBody RegistrationEO registrationEO) {
		try {
			String msg = registrationService.persistRegistrationEO(registrationEO);
			return ResponseEntity.status(HttpStatus.CREATED).body(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding registration: " + e.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateRegistration(@RequestBody RegistrationEO registrationEO) {
		try {
			String msg = registrationService.mergeRegistrationEO(registrationEO);
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating registration: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteRegistration(@PathVariable String id) {
		try {
			Boolean deleted = registrationService.removeRegistrationEO(id);
			if (deleted)
				return ResponseEntity.ok("Registration deleted successfully");
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registration not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting registration: " + e.getMessage());
		}
	}
}