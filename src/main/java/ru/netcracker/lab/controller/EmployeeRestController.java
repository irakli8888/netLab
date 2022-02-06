package ru.netcracker.lab.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.lab.model.api.request.EmployeeRequest;
import ru.netcracker.lab.model.api.response.EmployeeResponseWithList;
import ru.netcracker.lab.service.impl.EmployeeServiceImpl;
import ru.netcracker.lab.model.api.response.EmployeeResponse;

@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@RestController
public class EmployeeRestController {

    private final EmployeeServiceImpl employeeService;

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> save(@RequestBody EmployeeRequest request){
       return employeeService.save(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> delete(@PathVariable Long id){
        return employeeService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeRequest request, @PathVariable Long id){
        return employeeService.update(request, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Long id){
        return employeeService.findById(id);
    }

    @GetMapping("/")
    public ResponseEntity<EmployeeResponseWithList> findAll(){
        return employeeService.findAll();
    }
    //findAllByCriteria

    @GetMapping("/criteria")
    public ResponseEntity<EmployeeResponseWithList> findAllByCriteria(@RequestBody EmployeeRequest request){
        return employeeService.findAllByCriteria(request);
    }



}
