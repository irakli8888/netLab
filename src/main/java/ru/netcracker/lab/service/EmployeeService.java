package ru.netcracker.lab.service;

import org.springframework.http.ResponseEntity;
import ru.netcracker.lab.model.api.request.EmployeeRequest;
import ru.netcracker.lab.model.api.response.EmployeeResponse;
import ru.netcracker.lab.model.api.response.EmployeeResponseWithList;

public interface EmployeeService {

    ResponseEntity<EmployeeResponse> save(EmployeeRequest request);

    ResponseEntity<EmployeeResponse> delete(Long id);

    ResponseEntity<EmployeeResponse> update(EmployeeRequest request, Long id);

    ResponseEntity<EmployeeResponse> findById(Long id);

    ResponseEntity<EmployeeResponseWithList> findAll();

    ResponseEntity<EmployeeResponseWithList> findAllByCriteria(EmployeeRequest request);
}
