package ru.netcracker.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.lab.model.api.request.DepartmentRequest;
import ru.netcracker.lab.model.api.response.DepartmentResponse;
import ru.netcracker.lab.model.api.response.DepartmentResponseWithList;
import ru.netcracker.lab.service.impl.DepartmentServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentRestController {
    private final DepartmentServiceImpl departmentService;

    @PostMapping("/")
    public ResponseEntity<DepartmentResponse> save(@RequestBody DepartmentRequest request) {
        return departmentService.save(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentResponse> delete(@PathVariable Long id) {
        return departmentService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(@RequestBody DepartmentRequest request, @PathVariable Long id) {
        return departmentService.update(request, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @GetMapping("/")
    public ResponseEntity<DepartmentResponseWithList> findAll() {
        return departmentService.findAll();
    }
}
