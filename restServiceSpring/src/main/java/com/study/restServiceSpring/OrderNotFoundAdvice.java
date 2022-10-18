/*
* this class set a default response at client's side  
* when server throws an OrderNotFoundException
*/

package com.study.restServiceSpring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class OrderNotFoundAdvice {

    /*
     * @ResponseBody signals that this advice is rendered straight into the response
     * body.
     */
    @ResponseBody
    /*
     * @ExceptionHandler configures the advice to only respond if an
     * EmployeeNotFoundException is thrown.
     */
    @ExceptionHandler(OrderNotFoundException.class)
    /*
     * @ResponseStatus says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)

    String orderNotFoundHandler(OrderNotFoundException ex) {
        return ex.getMessage();
    }
}
