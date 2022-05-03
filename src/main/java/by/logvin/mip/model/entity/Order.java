package by.logvin.mip.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "solder_id")
    private Employee solder;

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @Column(name = "grand_total")
    private Double grandTotal;
}
