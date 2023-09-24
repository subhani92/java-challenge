package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves a list of employees.
     *
     * @return List of employees
     */
    @GetMapping
    @Cacheable("employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param employeeId The ID of the employee to retrieve
     * @return The employee with the specified ID or 404 Not Found if not found
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Saves a new employee.
     *
     * @param employee The employee to be saved
     * @return 201 Created if successful, 400 Bad Request if invalid data
     */
    @PostMapping
    public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) {
        if (employee == null) {
            return ResponseEntity.badRequest().build();
        }
        employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Deletes an employee by ID.
     *
     * @param employeeId The ID of the employee to delete
     * @return 204 No Content if successful, 404 Not Found if not found
     */
    @DeleteMapping("/{employeeId}")
    @CacheEvict(value = "employees", allEntries = true)
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        boolean deleted = employeeService.deleteEmployee(employeeId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing employee by ID.
     *
     * @param employeeId The ID of the employee to update
     * @param employee   The updated employee data
     * @return 204 No Content if successful, 400 Bad Request if invalid data,
     *         404 Not Found if employee not found
     */
    @PutMapping("/{employeeId}")
    @CacheEvict(value = "employees", allEntries = true)
    public ResponseEntity<Void> updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody Employee employee) {
        if (employee == null) {
            return ResponseEntity.badRequest().build();
        }
        Employee existingEmployee = employeeService.getEmployee(employeeId);
        if (existingEmployee != null) {
            employeeService.updateEmployee(employee);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
