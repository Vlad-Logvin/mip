package by.logvin.mip.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "drugs")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private String price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;
}
