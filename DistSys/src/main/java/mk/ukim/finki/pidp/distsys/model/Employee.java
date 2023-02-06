package mk.ukim.finki.pidp.distsys.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    private Long ssn;
    private String firstName;
    private String lastName;

    @OneToOne
    private User user;
    private LocalDate dateEmployment;
    private String isAdmin;
    private String gender;
    private Integer age;
    private Double grossWage;
    private Double contributions;
    private Double salary;

    public Employee() {

    }

    public Employee(Long ssn, String fName, String lName, User user, LocalDate dateEmployment, String isAdmin, String gender, Integer age, Double grossWage) {
        this.ssn = ssn;
        this.firstName = fName;
        this.lastName = lName;
        this.user = user;
        this.dateEmployment = dateEmployment;
        this.isAdmin = isAdmin;
        this.gender = gender;
        this.age = age;
        this.grossWage = grossWage;
        this.contributions = 0.3 * grossWage;
        this.salary = grossWage - contributions;
    }
}
