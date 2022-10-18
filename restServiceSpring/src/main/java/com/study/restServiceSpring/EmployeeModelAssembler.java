/*
 * Assembler class that holds the logic to assemble entity models using model Employee,
 * this entity models conform a HAL (HYPERTEX APLICATION LANGUAGE), containing links to 
 * access resources and representate actions at client's side. 
 * 
 * EX:
 * 
 * http://localhost:8080/images/
 * http://localhost:8080/images/{id}
 * http://localhost:8080/images/{id}/eliminar
 * http://localhost:8080/images/{id}/actualizar
 * 
 * This is one of the core concepts in order to make a RESTFUL API. 
 */

package com.study.restServiceSpring;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee) {

        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
}
