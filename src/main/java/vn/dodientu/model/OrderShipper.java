package vn.dodientu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_shipper")
public class OrderShipper {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)  // Áp dụng cascade cho việc xóa
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)  // Áp dụng cascade cho việc xóa
    @JoinColumn(name = "shipper_id", referencedColumnName = "id")
    private Shipper shipper;

    @Column(name = "assigned_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date assignedDate = new java.util.Date();

    private String status;
}
