package org.srikanth.inventory.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.srikanth.inventory.model.Inventory;
import org.srikanth.inventory.model.Order;
import org.srikanth.inventory.repository.InventoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrderConsumer {
    private final Logger log= LoggerFactory.getLogger(OrderConsumer.class);
    private final InventoryRepository inventoryRepository;

    @KafkaListener(topics = "order-topic",groupId = "inventory-group")
    public void consume(Order order) {
log.info("Order received in Inventory Service: {}", order);
        Optional<Inventory> inventoryopt=inventoryRepository.findByProductId(order.getProductId());
        if(inventoryopt.isPresent()) {
            Inventory inventory=inventoryopt.get();
            if (inventory.getAvilableQuantity() >= order.getQuantity())
            {
                inventory.setAvilableQuantity(inventory.getAvilableQuantity()- order.getQuantity());
                inventoryRepository.save(inventory);
                log.info("order processed stock available.Remaining: {}", order.getOrderId(),inventory.getAvilableQuantity());
            } else
            {
                log.warn("order cannot be processed-not enoughstocK!", order.getOrderId());
            }

        } else {
            log.warn("order cannot be processed-not enoughstocK!", order.getOrderId());

        }


    }
}
