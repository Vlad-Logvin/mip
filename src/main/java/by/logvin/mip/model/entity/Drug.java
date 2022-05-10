package by.logvin.mip.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "drugs")
@SQLDelete(sql = "UPDATE drugs SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "price", columnDefinition = "numeric(18,2)")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
}
