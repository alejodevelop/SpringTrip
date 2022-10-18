/*
 * Assembler class that holds the logic to assemble entity models using model Order,
 * this entity model conform a HAL (HYPERTEX APLICATION LANGUAGE), containing links to 
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
class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<Order> orderModel = EntityModel.of(order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        // Conditional links based on state of the order

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }
}
