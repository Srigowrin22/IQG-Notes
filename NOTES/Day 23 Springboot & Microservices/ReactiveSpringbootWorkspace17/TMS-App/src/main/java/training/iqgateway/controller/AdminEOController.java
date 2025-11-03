package training.iqgateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.servicesimpl.AdminEOServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminEOController {

    @Autowired
    private AdminEOServiceImpl adminEOServiceImpl;

    // GET /admin - fetch all admins
    @GetMapping
    public ResponseEntity<List<AdminEO>> fetchAllAdmin() {
        List<AdminEO> admins = adminEOServiceImpl.getAll();
        return ResponseEntity.ok(admins);
    }

    // GET /admin/{id} - fetch admin by designationId
    @GetMapping("/{id}")
    public ResponseEntity<AdminEO> fetchAdminById(@PathVariable("id") String id) {
        AdminEO admin = adminEOServiceImpl.getByIdd(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /admin - add new admin (id in body)
    @PostMapping
    public ResponseEntity<AdminEO> addAdmin(@RequestBody AdminEO adminEO) {
        AdminEO savedAdmin = adminEOServiceImpl.addAdmin(adminEO);
        return ResponseEntity.status(201).body(savedAdmin);
    }

    // PUT /admin/{id} - update admin with given designationId
    @PutMapping("/{id}")
    public ResponseEntity<AdminEO> updateAdmin(@PathVariable("id") String id, @RequestBody AdminEO adminEO) {
        AdminEO existingAdmin = adminEOServiceImpl.getByIdd(id);
        if (existingAdmin == null) {
            return ResponseEntity.notFound().build();
        }
        // You might want to copy properties from adminEO to existingAdmin here
        // For simplicity, assuming adminEO contains updated data and correct id
        adminEO.setDesignationId(id); // Ensure ID consistency
        AdminEO updatedAdmin = adminEOServiceImpl.updateAdmin(adminEO);
        return ResponseEntity.ok(updatedAdmin);
    }

    // DELETE /admin/{id} - delete admin by designationId
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") String id) {
        AdminEO existingAdmin = adminEOServiceImpl.getByIdd(id);
        if (existingAdmin == null) {
            return ResponseEntity.notFound().build();
        }
        adminEOServiceImpl.delete(existingAdmin);
        return ResponseEntity.noContent().build();
    }
}
