package vn.dodientu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderShipperId implements Serializable {
    private long orderId;
    private long shipperId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderShipperId that = (OrderShipperId) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(shipperId, that.shipperId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shipperId);
    }
}
