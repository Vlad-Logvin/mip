package by.logvin.mip.model.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "receipts")
@Data
@SQLDelete(sql = "UPDATE receipts SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solder_id", nullable = false)
    private User solder;

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @Column(name = "grand_total", columnDefinition = "numeric(18,2)")
    private Double grandTotal;

    @OneToMany(mappedBy = "receipt")
    private Set<Order> orders;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;
}
