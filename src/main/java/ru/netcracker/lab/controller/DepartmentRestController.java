package ru.netcracker.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.lab.model.api.request.DepartmentRequest;
import ru.netcracker.lab.model.api.request.EmployeeRequest;
import ru.netcracker.lab.model.api.response.DepartmentResponse;
import ru.netcracker.lab.model.api.response.DepartmentResponseWithList;
import ru.netcracker.lab.model.api.response.EmployeeResponse;
import ru.netcracker.lab.model.api.response.EmployeeResponseWithList;
import ru.netcracker.lab.service.impl.DepartmentServiceImpl;

@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
@RestController
public class DepartmentRestController {
    private final DepartmentServiceImpl departmentService;

    @PostMapping("/")
    public ResponseEntity<DepartmentResponse> save(@RequestBody DepartmentRequest request){
        //return departmentService.save(request);
        return  null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentResponse> delete(@PathVariable Long id){
        //return departmentService.delete(id);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(@RequestBody DepartmentRequest request, @PathVariable Long id){
        //return departmentService.update(request, id);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> findById(@PathVariable Long id){
        //return departmentService.findById(id);
        return null;
    }

    @GetMapping("/")
    public ResponseEntity<DepartmentResponseWithList> findAll(){
        //return departmentService.findAll();
        return null;
    }
}
