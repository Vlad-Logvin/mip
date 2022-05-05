package by.logvin.mip.model.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "reports")
@Data
@SQLDelete(sql = "UPDATE reports SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(
            name = "m2m_reports_receipts",
            joinColumns = {@JoinColumn(name = "report_id")},
            inverseJoinColumns = {@JoinColumn(name = "receipt_id")}
    )
    private Set<Receipt> receipts;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
