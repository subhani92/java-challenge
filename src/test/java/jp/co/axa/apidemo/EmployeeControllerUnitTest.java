package jp.co.axa.apidemo;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.http.HttpStatus.*;

public class EmployeeControllerUnitTest {

    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    public void testGetEmployees() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee());
        mockEmployees.add(new Employee());

        when(employeeService.retrieveEmployees()).thenReturn(mockEmployees);

        ResponseEntity<List<Employee>> responseEntity = employeeController.getEmployees();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size()); // Check the size of the list in the ResponseEntity
    }


    @Test
    public void testGetEmployee() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setName("John Doe");

        when(employeeService.getEmployee(employeeId)).thenReturn(mockEmployee);

        ResponseEntity<Employee> result = employeeController.getEmployee(employeeId);

        assertEquals(OK, result.getStatusCode());
        assertEquals(employeeId, result.getBody().getId());
        assertEquals("John Doe", result.getBody().getName());
    }

    @Test
    public void testGetEmployee_NotFound() {
        Long employeeId = 1L;

        // Simulate that the service returns null when the employee is not found
        when(employeeService.getEmployee(employeeId)).thenReturn(null);

        ResponseEntity<Employee> result = employeeController.getEmployee(employeeId);

        assertEquals(NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void testSaveEmployee() {
        // Create a mock employee to save
        Employee mockEmployeeToSave = new Employee();
        mockEmployeeToSave.setName("John Doe");
        mockEmployeeToSave.setSalary(50000);
        mockEmployeeToSave.setDepartment("HR");

        // Mock the service to perform an action when saveEmployee is called
        doAnswer(invocation -> {
            Employee savedEmployee = invocation.getArgument(0);
            savedEmployee.setId(1L); // Simulate the ID assignment by the service
            return null; // Since saveEmployee is void, return null
        }).when(employeeService).saveEmployee(any(Employee.class));

        // Call the controller to save the employee
        ResponseEntity<Void> responseEntity = employeeController.saveEmployee(mockEmployeeToSave);

        // Assert HTTP status code and response
        assertEquals(CREATED, responseEntity.getStatusCode());

        // Optionally, you can assert other conditions, such as the location header, if applicable.
    }
    
    @Test
    public void testDeleteEmployee() {
        Long employeeIdToDelete = 1L;

        // Mock the service to return true when an employee is successfully deleted
        when(employeeService.deleteEmployee(employeeIdToDelete)).thenReturn(true);

        // Call the controller to delete the employee
        ResponseEntity<Void> responseEntity = employeeController.deleteEmployee(employeeIdToDelete);

        // Assert HTTP status code and response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Optionally, you can assert other conditions based on your application's logic.
    }

    @Test
    public void testDeleteEmployee_NotFound() {
        Long nonExistentEmployeeId = 100L;

        // Mock the service to return false when attempting to delete a non-existent employee
        when(employeeService.deleteEmployee(nonExistentEmployeeId)).thenReturn(false);

        // Call the controller to delete the non-existent employee
        ResponseEntity<Void> responseEntity = employeeController.deleteEmployee(nonExistentEmployeeId);

        // Assert HTTP status code (404 Not Found)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Optionally, you can assert other conditions based on your application's logic.
    }

    @Test
    public void testUpdateEmployee() {
        Long employeeIdToUpdate = 1L;

        // Create a mock employee with updated data
        Employee mockEmployeeToUpdate = new Employee();
        mockEmployeeToUpdate.setName("Updated Name");
        mockEmployeeToUpdate.setSalary(60000);
        mockEmployeeToUpdate.setDepartment("IT");

        // Mock the service to return the updated employee
        when(employeeService.getEmployee(employeeIdToUpdate)).thenReturn(mockEmployeeToUpdate);

        // Call the controller to update the employee
        ResponseEntity<Void> responseEntity = employeeController.updateEmployee(employeeIdToUpdate, mockEmployeeToUpdate);

        // Assert HTTP status code (204 No Content)
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Optionally, you can assert other conditions based on your application's logic.
    }

    @Test
    public void testUpdateEmployee_NotFound() {
        Long nonExistentEmployeeId = 100L;

        // Mock the service to return null when attempting to update a non-existent employee
        when(employeeService.getEmployee(nonExistentEmployeeId)).thenReturn(null);

        // Call the controller to update the non-existent employee
        ResponseEntity<Void> responseEntity = employeeController.updateEmployee(nonExistentEmployeeId, new Employee());

        // Assert HTTP status code (404 Not Found)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Optionally, you can assert other conditions based on your application's logic.
    }

}
