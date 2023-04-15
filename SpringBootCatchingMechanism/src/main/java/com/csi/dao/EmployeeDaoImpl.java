package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl{

    @Autowired
    EmployeeRepo employeeRepoImpl;
    public Employee signUp(Employee employee){

       return employeeRepoImpl.save(employee);
    }

    public boolean signIn(String empEmailId,String empPassword){
        boolean flag=false;

        for(Employee employee: getAllData()){
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
                flag =true;
            }
        }

        return flag;
    }

    public Optional<Employee>  getDataById(int empId){

        return employeeRepoImpl.findById(empId);
    }

    public List<Employee> getAllData(){
        return employeeRepoImpl.findAll();
    }

    public List<Employee> getDataByName(String empName){
        return employeeRepoImpl.findByEmpName(empName);
    }

    public Employee updateData(Employee employee){
        return  employeeRepoImpl.save(employee);
    }
    public void deleteDataById(int empId){
        employeeRepoImpl.deleteById(empId);
    }
    public void deleteAllData(){
        employeeRepoImpl.deleteAll();
    }
}
