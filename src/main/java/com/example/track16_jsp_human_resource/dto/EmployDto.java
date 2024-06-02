package com.example.track16_jsp_human_resource.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmployDto {
    private String no;
    private String name;
    private String grade;
    private String depart;
    private int age;
}
