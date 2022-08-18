package com.ems.employeeservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "EmpID", nullable = false)
    private long eid;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "project")
    private String project;

    private void release(){
        this.project = null;
    }
}
