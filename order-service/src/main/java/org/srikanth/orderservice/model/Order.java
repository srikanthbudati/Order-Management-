package org.srikanth.orderservice.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    private Integer quantity;
    private String status;
}
