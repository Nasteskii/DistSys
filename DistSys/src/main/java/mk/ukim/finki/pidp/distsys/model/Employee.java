package mk.ukim.finki.pidp.distsys.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    private Long ssn;
    private String username;
    private String password;
    private LocalDate dateEmployed;
    private Boolean hasAdminAccess;
    private Integer age;
    private Double salary;
}