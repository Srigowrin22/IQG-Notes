package training.iqgateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import training.iqgateway.dto.OwnerDTO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/all")
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        List<OwnerDTO> owners = ownerService.findAllOwners();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{aadhar}")
    public ResponseEntity<OwnerEO> getOwnerByAadhar(@PathVariable String aadhar) {
        OwnerEO owner = ownerService.findOwnerByAadhar(aadhar);
        if (owner != null) {
            return ResponseEntity.ok(owner);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<OwnerDTO>> getOwnersByName(@PathVariable String name) {
        List<OwnerDTO> owners = ownerService.findOwnerByName(name);
        return ResponseEntity.ok(owners);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOwner(@RequestBody OwnerEO ownerEO) {
        try {
            String msg = ownerService.persistOwnerEO(ownerEO);
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding owner: " + ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOwner(@RequestBody OwnerEO ownerEO) {
        try {
            String msg = ownerService.mergeOwnerEO(ownerEO);
            return ResponseEntity.ok(msg);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating owner: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOwner(@RequestParam String ownerAadhar, @RequestParam String password) {
        try {
            Boolean deleted = ownerService.removeOwnerEO(ownerAadhar, password);
            if (deleted) return ResponseEntity.ok("Owner deleted successfully");
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete owner");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting owner: " + ex.getMessage());
        }
    }
}