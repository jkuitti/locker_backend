package org.example.lockerapp.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "assingments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assigned_at", nullable = false)
    private LocalDateTime assignedAt;

    @Column(name = "employee_last_name", nullable = false)
    private String employeeLastName;

    @Column(name = "employee_first_name", nullable = false)
    private String employeeFirstName;

    @OneToOne
    @JoinColumn(name = "locker_id", nullable = false, unique = true)
    private Locker locker;


    public Assignment() {
    }

    public Assignment(Long id, LocalDateTime assignedAt, String employeeLastName, String employeeFirstName, Locker locker) {
        this.id = id;
        this.assignedAt = assignedAt;
        this.employeeLastName = employeeLastName;
        this.employeeFirstName = employeeFirstName;
        this.locker = locker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    @Override
    public String toString() {
        return "Assignments{" +
                "id=" + id +
                ", assignedAt=" + assignedAt +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", locker=" + locker +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
