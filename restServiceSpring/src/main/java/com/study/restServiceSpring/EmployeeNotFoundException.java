/*
 * class that creates a runtime exception  
 */

package com.study.restServiceSpring;

class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}