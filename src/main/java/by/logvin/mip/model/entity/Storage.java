package by.logvin.mip.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "address")
    private String address;

    @OneToMany
    private List<Drug> drugs;
}
