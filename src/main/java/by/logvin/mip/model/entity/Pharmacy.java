package by.logvin.mip.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pharmacies")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "pharmacy")
    private Set<Storage> storages;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
