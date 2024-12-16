package vn.dodientu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User user;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "address_id")
    private Address shippingAddress;
    
    private Double totalAmount;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    private String note;
    
    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_status")
    //Trạng thái giao hàng
    private OrderShippingStatus shippingStatus;
    

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt = new java.util.Date();

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt = new java.util.Date();

    @Column(name = "order_date" )
    private LocalDateTime orderDate;

    @Column(name = "receive_date")
    private LocalDateTime  receiveDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<LineItem> listLineItems;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    @JsonManagedReference
    private Payment payment;
    
    
   
    
}
