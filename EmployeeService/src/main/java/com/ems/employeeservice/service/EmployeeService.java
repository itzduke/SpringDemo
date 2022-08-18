package com.ems.employeeservice.service;

import com.ems.employeeservice.dto.EmployeeDTO;
import com.ems.employeeservice.entity.Employee;

public interface EmployeeService {


    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    public EmployeeDTO findEmployee(long id);
    public EmployeeDTO toDTO(Employee employee);
    public EmployeeDTO assignProject(EmployeeDTO employeeDTO);
    public EmployeeDTO releaseProject(EmployeeDTO employeeDTO);
    public EmployeeDTO autoGenerateEmail(EmployeeDTO employeeDTO);
    public Boolean empExists(EmployeeDTO employeeDTO);
    public Boolean empExists(long id);
}
