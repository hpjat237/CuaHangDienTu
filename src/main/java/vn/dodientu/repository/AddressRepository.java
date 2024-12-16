package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.dodientu.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
