package org.srikanth.orderservice.repository;
import org.srikanth.orderservice.model.Order;


import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
