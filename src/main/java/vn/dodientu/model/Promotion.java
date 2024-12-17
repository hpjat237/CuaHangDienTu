package vn.dodientu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)  // Áp dụng cascade cho việc xóa
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    private String code;
    private Double discountPercentage;
    private java.util.Date startDate;
    private java.util.Date endDate;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt = new java.util.Date();
}
