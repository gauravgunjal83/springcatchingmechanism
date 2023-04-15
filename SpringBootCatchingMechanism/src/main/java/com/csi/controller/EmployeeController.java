package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")

    public ResponseEntity<Employee> signUp(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeServiceImpl.signUp(employee)) ;
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId,@PathVariable String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId,empPassword));
    }

    @GetMapping("/getDataById/{empId}")

    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId){
        return  ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>getAllData(){
       return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/getdatabyname/{empName}")

    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empName){
        return  ResponseEntity.ok(employeeServiceImpl.getDataByName(empName));
    }
    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortDataBySalary(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }

    @GetMapping("/filterbycontactnumber/{empContactNumber}")

    public  ResponseEntity<List<Employee>>filterByContactNumber(@PathVariable long empContactNumber){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp->emp.getEmpContactNumber()==empContactNumber).collect(Collectors.toList()));
    }
    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary){
        return  ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp-> emp.getEmpSalary()==empSalary).collect(Collectors.toList()));
    }

    @PutMapping("/updatedata/{empId}")

    public ResponseEntity<Employee>updateData(@PathVariable int empId,@RequestBody Employee employee){
        Employee employee1=employeeServiceImpl.getDataById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id does not exist ")) ;

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());

        return  ResponseEntity.ok(employeeServiceImpl.updateData(employee1));
    }

    @DeleteMapping("/deletedatabyid/{empId}")

    public ResponseEntity<String> deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")

    public ResponseEntity<String> deleteAllData(){
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("Data Deleted successfully");
    }
}
