package com.ems.employeeservice.controller;

import com.ems.employeeservice.dto.EmployeeDTO;
import com.ems.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Employee")

public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        super();
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO temp = employeeService.addEmployee(employeeDTO);
        if (temp == null){
            temp = employeeService.findEmployee(employeeDTO.getEid());
            return new ResponseEntity<String>("Already exists\n"+temp.toString(),HttpStatus.NOT_ACCEPTABLE);
        }
        else
            return new ResponseEntity<EmployeeDTO>(temp,HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findEmployee(@RequestBody EmployeeDTO employeeDTO){

        EmployeeDTO emp = employeeService.findEmployee(employeeDTO.getEid());
        if(emp == null){
            return new ResponseEntity<String>("Employee not Found", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<EmployeeDTO>(emp, HttpStatus.OK);
        }

    }

    @PutMapping("/assign")
    public ResponseEntity<?> assignProject(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO emp = employeeService.assignProject(employeeDTO);
        if(emp == null){
            return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<EmployeeDTO>(emp, HttpStatus.OK);
    }

    @PutMapping("/release")
    public ResponseEntity<?> releaseProject(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO temp = employeeService.releaseProject(employeeDTO);
        if(temp == null)
        {
            return new ResponseEntity<String>("Project is not assigned", HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<EmployeeDTO>(temp, HttpStatus.OK);
    }

    @PutMapping("/automail")
    public ResponseEntity<?> autoAssignEmail(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO temp = employeeService.autoGenerateEmail(employeeDTO);
        if (temp == null){
            return new ResponseEntity<String>("Email already exixsts", HttpStatus.NOT_ACCEPTABLE);
        }
        else
            return new ResponseEntity<EmployeeDTO>(temp, HttpStatus.OK);
    }

}
