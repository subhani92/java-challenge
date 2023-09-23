package jp.co.axa.apidemo;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class EmployeeControllerUnitTest {

    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeController = new EmployeeController();
        employeeController.setEmployeeService(employeeService);
    }

    @Test
    public void testGetEmployees() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee());
        mockEmployees.add(new Employee());

        when(employeeService.retrieveEmployees()).thenReturn(mockEmployees);

        List<Employee> result = employeeController.getEmployees();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetEmployee() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setName("John Doe");

        when(employeeService.getEmployee(employeeId)).thenReturn(mockEmployee);

        Employee result = employeeController.getEmployee(employeeId);

        assertEquals(employeeId, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testGetEmployee_NotFound() {
        Long employeeId = 1L;

        // Simulate that the service returns null when the employee is not found
        when(employeeService.getEmployee(employeeId)).thenReturn(null);

        Employee result = employeeController.getEmployee(employeeId);

        assertNull(result);
    }
}
