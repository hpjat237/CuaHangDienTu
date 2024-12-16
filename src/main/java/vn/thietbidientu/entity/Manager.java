package vn.thietbidientu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.thietbidientu.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
public class Manager extends User implements Serializable {
}
