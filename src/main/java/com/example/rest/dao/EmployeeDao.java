package com.example.rest.dao;

import com.example.rest.model.Employee;
import com.example.rest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDao {

    @Autowired
    EmployeeRepository repository;

    public Employee save(Employee emp) {
        return repository.save(emp);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Optional<Employee> findById(Long empId) {
        return repository.findById(empId);
    }

    public void delete(Employee emp) {
        repository.delete(emp);
    }

}
