package com.example.rest.controller;

import com.example.rest.dao.EmployeeDao;
import com.example.rest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) {
        Employee employee =  employeeDao.getOne(id);

        if(null == employee) {
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok().body(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody Employee empDetails) {
        Employee employee = employeeDao.getOne(id);

        if(null == employee) {
            return ResponseEntity.notFound().build();
        }

        employee.setName(empDetails.getName());
        employee.setDesignation(empDetails.getDesignation());
        employee.setExpertise(empDetails.getExpertise());

        Employee savedEmployee = employeeDao.save(employee);

        return ResponseEntity.ok().body(savedEmployee);

    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> delete(@PathVariable(value = "id") Long id) {
        Employee employee = employeeDao.getOne(id);

        if(null == employee) {
            return ResponseEntity.notFound().build();
        }

        employeeDao.delete(employee);

        return ResponseEntity.ok().build();
    }
}
