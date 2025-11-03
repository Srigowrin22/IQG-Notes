package training.iqgateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import training.iqgateway.dto.VehicleOffenceDTO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.service.VehicleOffenceService;

@RestController
@RequestMapping("/vehicleOffence")
public class VehicleOffenceController {

    @Autowired
    private VehicleOffenceService vehicleOffenceService;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleOffenceDTO>> getAllVehicleOffences() {
        List<VehicleOffenceDTO> offences = vehicleOffenceService.findAllVehicleOffences();
        return ResponseEntity.ok(offences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleOffenceDTO> getVehicleOffenceById(@PathVariable Integer id) {
        VehicleOffenceDTO offence = vehicleOffenceService.findVehicleOffenceByID(id);
        if (offence != null) {
            return ResponseEntity.ok(offence);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/byRegistration/{regId}")
    public ResponseEntity<List<VehicleOffenceDTO>> getVehicleOffencesByRegistrationId(@PathVariable String regId) {
        List<VehicleOffenceDTO> offences = vehicleOffenceService.findVehicleOffenceByRegisID(regId);
        return ResponseEntity.ok(offences);
    }

    @GetMapping("/byStatus")
    public ResponseEntity<List<VehicleOffenceDTO>> getVehicleOffencesByStatus(
            @RequestParam String registrationID,
            @RequestParam Integer status) {
        List<VehicleOffenceDTO> offences = vehicleOffenceService.findVehicleOffenceByStatus(registrationID, status);
        return ResponseEntity.ok(offences);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addVehicleOffence(@RequestBody VehicleOffenceEO vehicleOffenceEO) {
        try {
            String msg = vehicleOffenceService.persistVehicleOffenceEO(vehicleOffenceEO);
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding vehicle offence: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateVehicleOffence(@RequestBody VehicleOffenceEO vehicleOffenceEO) {
        try {
            String msg = vehicleOffenceService.mergeVehicleOffenceEO(vehicleOffenceEO);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating vehicle offence: " + e.getMessage());
        }
    }

    @PutMapping("/clear")
    public ResponseEntity<String> clearVehicleOffence(@RequestBody VehicleOffenceEO vehicleOffenceEO) {
        try {
            String msg = vehicleOffenceService.clearVehicleOffenceEO(vehicleOffenceEO);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error clearing vehicle offence: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteVehicleOffence(@PathVariable Integer id) {
        try {
            Boolean deleted = vehicleOffenceService.removeVehicleOffenceEO(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
