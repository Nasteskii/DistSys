package mk.ukim.finki.pidp.distsys.service;

import mk.ukim.finki.pidp.distsys.model.Employee;
import mk.ukim.finki.pidp.distsys.model.User;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();

    String edit(Long ssn, Employee employee);

    String save(Employee employee);

    Optional<Employee> findBySsn(Long ssn);
}
