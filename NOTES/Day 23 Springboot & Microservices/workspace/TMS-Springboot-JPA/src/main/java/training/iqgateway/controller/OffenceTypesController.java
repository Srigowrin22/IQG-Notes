package training.iqgateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import training.iqgateway.dto.OffenceTypesDTO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.service.OffenceTypesService;

@RestController
@RequestMapping("/offenceTypes")
public class OffenceTypesController {

	@Autowired
	private OffenceTypesService offenceTypesService;

	@GetMapping("/showAll")
	public ResponseEntity<List<OffenceTypesDTO>> fetchAllOffenceTypes() {
		List<OffenceTypesDTO> list = offenceTypesService.getOffenceTypeEOFindAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addOffenceType(@RequestBody OffenceTypesEO offenceEO) {
		try {
			String res = offenceTypesService.persistOffenceTypeEO(offenceEO);
			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Failed to create offence type: " + e.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateOffenceType(@RequestBody OffenceTypesEO offenceEO) {
		try {
			String res = offenceTypesService.mergeOffenceTypeEO(offenceEO);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Failed to update offence type: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteOffenceType(@PathVariable Long id) {
		OffenceTypesEO offenceEO = new OffenceTypesEO();
		offenceEO.setOffenceId(id);
		Boolean deleted = offenceTypesService.removeOffenceTypeEO(offenceEO);
		return ResponseEntity.ok(deleted);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<OffenceTypesEO> findOffenceTypeByID(@PathVariable Integer id) {
		OffenceTypesEO offence = offenceTypesService.findOffenceTypeByID(id);
		if (offence != null) {
			return ResponseEntity.ok(offence);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/findByVehicleType/{vehicleType}")
	public ResponseEntity<List<OffenceTypesDTO>> findByVehicleType(@PathVariable String vehicleType) {
		List<OffenceTypesDTO> list = offenceTypesService.findOffenceByVehicleType(vehicleType);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/findByOffenceType/{offenceType}")
	public ResponseEntity<List<OffenceTypesDTO>> findByOffenceType(@PathVariable String offenceType) {
		List<OffenceTypesDTO> list = offenceTypesService.findOffenceByOffenceType(offenceType);
		return ResponseEntity.ok(list);
	}
}