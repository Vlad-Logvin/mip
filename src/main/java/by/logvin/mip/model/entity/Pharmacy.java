package by.logvin.mip.model.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pharmacies")
@Data
@SQLDelete(sql = "UPDATE pharmacies SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "pharmacy")
    private Set<Storage> storages;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;
}
