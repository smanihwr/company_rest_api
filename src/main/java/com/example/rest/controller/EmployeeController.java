package com.example.rest.controller;

import com.example.rest.dao.EmployeeDao;
import com.example.rest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeDao.save(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployess() {
        return employeeDao.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long id) {
        Optional<Employee> employee = employeeDao.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok().body(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody Employee empDetails) {

        Optional<Employee> employee = employeeDao.findById(id);
        if (employee.isPresent()) {
            Employee e = employee.get();
            e.setName(empDetails.getName());
            e.setDesignation(empDetails.getDesignation());
            e.setExpertise(empDetails.getExpertise());
            Employee savedEmployee = employeeDao.save(e);

            return ResponseEntity.ok().body(savedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> delete(@PathVariable(value = "id") Long id) {

        Optional<Employee> employee = employeeDao.findById(id);
        if (employee.isPresent()) {
            employeeDao.delete(employee.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
