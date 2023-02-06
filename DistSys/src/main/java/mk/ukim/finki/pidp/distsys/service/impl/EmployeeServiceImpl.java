package mk.ukim.finki.pidp.distsys.service.impl;

import mk.ukim.finki.pidp.distsys.model.Employee;
import mk.ukim.finki.pidp.distsys.repository.EmployeeRepository;
import mk.ukim.finki.pidp.distsys.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public String edit(Long ssn, Employee newEmployee) {
        Employee found = employeeRepository.findById(ssn).get();
        found.setFirstName(newEmployee.getFirstName());
        found.setLastName(newEmployee.getLastName());
        found.getUser().setUsername(newEmployee.getUser().getUsername());
        found.setDateEmployment(newEmployee.getDateEmployment());
        found.setIsAdmin(newEmployee.getIsAdmin());
        found.setGender(newEmployee.getGender());
        found.setAge(newEmployee.getAge());
        found.setSalary(newEmployee.getSalary());
        return save(found);
    }

    @Override
    public String save(Employee employee) {
        employeeRepository.save(employee);
        return "Employee " + employee.getFirstName() + " " + employee.getLastName() + " was saved!";
    }
    

    @Override
    public Optional<Employee> findBySsn(Long ssn) {
        return employeeRepository.findById(ssn);
    }
}
