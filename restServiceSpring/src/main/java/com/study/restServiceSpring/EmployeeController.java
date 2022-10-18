/*
* controller that handle requests make from client to server 
*/

package com.study.restServiceSpring;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
class EmployeeController {

    // constructor can be replaced with @Autowired
    private final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // ----------------------------------------------------------------------------

    // first function whiout links

    // @GetMapping("/employees")
    // List<Employee> all() {
    // return repository.findAll();
    // }

    // second function with links created in this controller

    // @GetMapping("/employees")
    // CollectionModel<EntityModel<Employee>> all() {

    // List<EntityModel<Employee>> employees = repository.findAll().stream()
    // .map(employee -> EntityModel.of(employee,
    // linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
    // linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
    // .collect(Collectors.toList());

    // return CollectionModel.of(employees,
    // linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    // }

    // third function with links created in the employeeModelAssembler class

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    // ----------------------------------------------------------------------------

    // @PostMapping("/employees")
    // Employee newEmployee(@RequestBody Employee newEmployee) {
    // return repository.save(newEmployee);
    // }

    // this method return the employee added with its respective hyperlinks

    /*
     * Spring MVC’s ResponseEntity is used to create an HTTP 201 Created status
     * message. This type of response typically includes a Location response header,
     * and we use the URI derived from the model’s self-related link.
     */
    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // ----------------------------------------------------------------------------

    // @GetMapping("/employees/{id}")
    // Employee one(@PathVariable Long id) {

    // return repository.findById(id)
    // .orElseThrow(() -> new EmployeeNotFoundException(id));

    // }

    // @GetMapping("/employees/{id}")
    // EntityModel<Employee> one(@PathVariable Long id) {

    // Employee employee = repository.findById(id) //
    // .orElseThrow(() -> new EmployeeNotFoundException(id));

    // // adding links
    // return EntityModel.of(employee, //
    // linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
    // linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    // }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        // already links added
        /*
         * except instead of creating the EntityModel<Employee> instance here, you
         * delegate it to the assembler
         */
        return assembler.toModel(employee);
    }

    // ----------------------------------------------------------------------------

    // @PutMapping("/employees/{id}")
    // Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable
    // Long id) {

    // return repository.findById(id)
    // .map(employee -> {
    // employee.setName(newEmployee.getName());
    // employee.setRole(newEmployee.getRole());
    // return repository.save(employee);
    // })
    // .orElseGet(() -> {
    // newEmployee.setId(id);
    // return repository.save(newEmployee);
    // });
    // }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        Employee updatedEmployee = repository.findById(id) //
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                }) //
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // ----------------------------------------------------------------------------

    // @DeleteMapping("/employees/{id}")
    // void deleteEmployee(@PathVariable Long id) {
    // repository.deleteById(id);
    // }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
