package ru.netcracker.lab.service;

import org.springframework.http.ResponseEntity;
import ru.netcracker.lab.model.Department;
import ru.netcracker.lab.model.api.request.DepartmentRequest;
import ru.netcracker.lab.model.api.response.DepartmentResponse;
import ru.netcracker.lab.model.api.response.DepartmentResponseWithList;

public interface DepartmentService {
    void add(String name, long headId);
    Department get(long id);
    ResponseEntity<DepartmentResponse> save(DepartmentRequest request);
    ResponseEntity<DepartmentResponse> delete(Long id);
    ResponseEntity<DepartmentResponse> update(DepartmentRequest request, Long id);
    ResponseEntity<DepartmentResponse> findById(Long id);
    ResponseEntity<DepartmentResponseWithList> findAll();
    ResponseEntity<DepartmentResponseWithList> findAllByCriteria(DepartmentRequest request);
}