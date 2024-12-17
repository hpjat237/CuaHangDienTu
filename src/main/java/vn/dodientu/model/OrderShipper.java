package vn.dodientu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_shipper")
public class OrderShipper {

    @EmbeddedId
    public OrderShipperId id;
    @Column(name = "assigned_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date assignedDate = new java.util.Date();

    private String status;
}
