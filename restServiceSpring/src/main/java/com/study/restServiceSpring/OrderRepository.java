/*
 * repository that allows to make basics DB operations 
 * create/replace/update/delete (H2 this case)
 */

package com.study.restServiceSpring;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<Order, Long> {
}