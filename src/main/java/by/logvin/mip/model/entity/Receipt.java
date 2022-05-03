package by.logvin.mip.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solder_id", nullable = false)
    private Employee solder;

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @Column(name = "grand_total")
    private Double grandTotal;

    @OneToMany(mappedBy = "receipt")
    private Set<Order> orders;
}
