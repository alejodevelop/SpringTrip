/*
 * class that creates a runtime exception  
 */

package com.study.restServiceSpring;

class OrderNotFoundException extends RuntimeException {

    OrderNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}