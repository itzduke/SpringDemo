package com.ems.employeeservice.service.impl;

import com.ems.employeeservice.dto.EmployeeDTO;
import com.ems.employeeservice.entity.Employee;
import com.ems.employeeservice.repository.EmployeeRepo;
import com.ems.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        if(empExists(employeeDTO)){
            return null;
        }
        else{
            if(employeeDTO.getEmail() == null)
                employeeDTO.setEmail(employeeDTO.getEid()+"@mail.com");
            return builderDTO(employeeRepo.save(toEntity(employeeDTO)));
        }
    }

    @Override
    public EmployeeDTO findEmployee(long id) {
        if(employeeRepo.existsByEid(id)){
            return toDTO(employeeRepo.findByEid(id));
        }
        else return null;
    }

    @Override
    public EmployeeDTO assignProject(EmployeeDTO employeeDTO) {
        if(!empExists(employeeDTO)){
            return null;
            }
        else {
            EmployeeDTO temp = toDTO(employeeRepo.findByEid(employeeDTO.getEid()));
            temp.setProject(employeeDTO.getProject());
            employeeRepo.save(toEntity(temp));
            return temp;
        }
    }

    @Override
    public EmployeeDTO releaseProject(EmployeeDTO employeeDTO) {
        if (!empExists(employeeDTO)){
            return null;
        }
        else {
            Employee employee = employeeRepo.findByEid(employeeDTO.getEid());
            employee.setProject("");
            return toDTO(employeeRepo.save(employee));
        }
    }

    @Override
    public EmployeeDTO autoGenerateEmail(EmployeeDTO employeeDTO) {
        Employee emp = employeeRepo.findByEid(employeeDTO.getEid());
        if (!empExists(employeeDTO)){
            return null;
        }
        if(emp.getEmail() == null || emp.getEmail().equals("")){
            Employee temp = employeeRepo.findByEid(employeeDTO.getEid());
            temp.setEmail(employeeDTO.getEid()+"@mail.com");
            return toDTO(employeeRepo.save(temp));
        }
        else{
            return null;
        }
    }

    @Override
    public Boolean empExists(EmployeeDTO employeeDTO) {
        return employeeRepo.existsByEid(employeeDTO.getEid());
    }
    @Override
    public Boolean empExists(long id) {
        return employeeRepo.existsByEid(id);
    }

    public Employee toEntity(EmployeeDTO employeeDTO){
        Employee employee;
        employee = modelMapper.map(employeeDTO, Employee.class);
        return employee;
    }
    public EmployeeDTO toDTO(Employee employee){
        EmployeeDTO employeeDTO;
        employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }
    public EmployeeDTO builderDTO(Employee employee){
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .eid(employee.getEid())
                .name(employee.getName())
                .email(employee.getEmail())
                .project(employee.getProject())
                .build();
        return employeeDTO;
    }
}
