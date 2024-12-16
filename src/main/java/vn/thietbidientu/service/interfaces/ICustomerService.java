package vn.thietbidientu.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.thietbidientu.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long id);

    void updateActiveStatus(Long userId, boolean status);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    Page<Customer> searchByKeyword(String keyword,Pageable pageable);

    void lockAccount(Long customerId);

    void unlockAccount(Long customerId);

    void toggleActiveStatus(Long customerId);

    void deleteCustomerById(Long customerId);

    Page<Customer> findAll(Pageable pageable);
}
