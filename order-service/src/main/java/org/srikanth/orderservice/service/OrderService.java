package org.srikanth.orderservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.srikanth.orderservice.model.Order;
import org.srikanth.orderservice.repository.OrderRepository;
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Order> kafkaTemplate;
    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, Order> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;

    }
    public Order placeOrder(Order order) {
        order.setStatus("Created");
        Order savedOrder=orderRepository.save(order);
        kafkaTemplate.send("order-topic",savedOrder);
        return savedOrder;
    }
}