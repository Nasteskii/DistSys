package mk.ukim.finki.pidp.distsys.repository;

import mk.ukim.finki.pidp.distsys.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
