package com.example.msemployer.Controllers;


import com.example.msemployer.Service.EmployerConsumerService;
import com.example.msemployer.dto.EmployerDto;
import com.example.msemployer.entities.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Employers")
@CrossOrigin
public class EmployerController {

    @Autowired
    private EmployerConsumerService employerConsumerService;

    @GetMapping("/all")
    public Iterable<Employer> getEmployers() {
        return employerConsumerService.getAllEmployers();
    }

//    post
//    @PostMapping("/add")
//    public Employer addEmployer(@RequestBody EmployerDto employerDto) {
//        return employerConsumerService.createEmployer(employerDto);
//    }

//    update
    @PutMapping("/update/{id}")
    public Employer updateEmployer(@PathVariable Long id,@RequestBody EmployerDto employerDto) {
        return employerConsumerService.updateEmployer(id,employerDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployer(@PathVariable Long id) {
        employerConsumerService.deleteEmployer(id);
    }

      @GetMapping("/{id}")
        public Employer getEmployerById(@PathVariable("id") Long id){
            return employerConsumerService.getEmployerById(id);
        }



}
