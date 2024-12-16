package vn.thietbidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.thietbidientu.entity.Address;
import vn.thietbidientu.entity.Customer;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByCustomer_UserId(Long userId);

    List<Address> findByCustomer(Customer customer);
}
