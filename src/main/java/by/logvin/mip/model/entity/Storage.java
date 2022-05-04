package by.logvin.mip.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pharmacy_address", length = 512)
    private String pharmacyAddress;

    @OneToMany(mappedBy = "storage")
    private List<Drug> drugs;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
