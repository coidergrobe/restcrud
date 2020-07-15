package com.example.demorest02.controller;

import com.example.demorest02.entity.Employee;
import com.example.demorest02.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-manager")
public class RestEmployee {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String index(){
        return "employees";
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return (List<Employee>)employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeRepository.findById(id).get();
    }


    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
    }

    @PutMapping("/employees/{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
      return employeeRepository.findById(id).map(employee -> {
           employee.setFirstName(newEmployee.getFirstName());
           employee.setLastName(newEmployee.getLastName());
           employee.setEmail(newEmployee.getEmail());
           return employeeRepository.save(employee);
       }).orElseGet(()->{
           newEmployee.setId(id);
           return employeeRepository.save(newEmployee);
       });
    }
}
