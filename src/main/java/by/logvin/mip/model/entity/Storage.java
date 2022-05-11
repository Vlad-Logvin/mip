package by.logvin.mip.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "storages")
@Data
@SQLDelete(sql = "UPDATE storages SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @JsonIdentityReference(alwaysAsId = true)
    private Pharmacy pharmacy;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;
}
