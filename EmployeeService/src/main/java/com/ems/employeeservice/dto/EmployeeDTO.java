package com.ems.employeeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {
    private long eid;
    private String name;
    private String project;
    private String email;

    

}
