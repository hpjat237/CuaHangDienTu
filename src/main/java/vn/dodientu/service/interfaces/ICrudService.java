package vn.dodientu.service.interfaces;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ICrudService<T, ID> {
    T add(T entity);
    Optional<T> findById(ID id);
    Page<T> findAllPagination(int page, int size);
    T update(T entity);
    void deleteById(ID id);
}
