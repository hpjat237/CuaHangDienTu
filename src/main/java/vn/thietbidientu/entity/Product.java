package vn.thietbidientu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(unique = true)
    private String productCode;
    @Column(columnDefinition = "text")
    private String productName;
    private Double cost;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String brand;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufactureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    @Column(columnDefinition = "text")
    private String ingredient;
    @Column(columnDefinition = "text")
    private String how_to_use;
    private String volume;
    @Column(columnDefinition = "text")
    private String origin;
    @Column(columnDefinition = "text")
    private String image;

    // phục vụ cho việc hiển thị sản phẩm mới ở trang chủ
    private LocalDateTime createdDate = LocalDateTime.now();
    private Boolean active=true;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ProductStock productStock;

}
