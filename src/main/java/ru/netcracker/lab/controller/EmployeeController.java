package ru.netcracker.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.lab.service.impl.EmployeeServiceImpl;

/**
 * @author IrklKvch
 * @author AverVit
 */
@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @GetMapping("/addEmp")
    public String employee(Model model){
        model.addAttribute("employee", employeeService.findAll());
        return "addEmployee";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id, Model model){
        model.addAttribute("employee_2", employeeService.get(id));
        return "getEmployee";
    }

    @PostMapping("/")
    public String add(@RequestParam String name, @RequestParam String phoneNumber,
                      @RequestParam Double salary, @RequestParam long department){
        employeeService.add(name, phoneNumber, salary, department);
        return "redirect:/employee/addEmp";
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){

    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id){

    }
}
