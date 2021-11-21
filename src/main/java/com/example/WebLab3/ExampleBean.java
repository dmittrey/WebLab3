package com.example.WebLab3;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
@Data
public class ExampleBean {

    private Employee newEmployee = new Employee();

    private List<Employee> employees = new ArrayList<>();

    public void addEmployee() {
        employees.add(newEmployee);
        newEmployee = new Employee();
    }
}