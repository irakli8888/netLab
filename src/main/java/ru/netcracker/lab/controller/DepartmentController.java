package ru.netcracker.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.lab.service.impl.DepartmentServiceImpl;

@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    @GetMapping("/addDep")
    public String department(Model model){
        model.addAttribute("department", departmentService.findAll());
        return "addDepartment";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id, Model model){
        model.addAttribute("department_2", departmentService.get(id));
        return "getDepartment";
    }

    @PostMapping("/")
    public String add(@RequestParam String name, @RequestParam long head){
        departmentService.add(name, head);
        return "redirect:/department/addDep";
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){

    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id){

    }
}
