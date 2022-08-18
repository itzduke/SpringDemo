package com.ems.employeeservice.repository;

import com.ems.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Boolean existsByEid(long eid);
    Employee findByEid(long eid);
    Boolean existsByEmail(String email);

}
