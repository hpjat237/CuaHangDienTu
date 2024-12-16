package vn.thietbidientu.service.interfaces;

import vn.thietbidientu.entity.Category;
import vn.thietbidientu.payload.response.CategoryProductPagingResponse;
import vn.thietbidientu.payload.response.CategoryProductResponse;
import vn.thietbidientu.payload.response.CategoryResponse;
import vn.thietbidientu.payload.response.CategorySalesResp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> getAllCategories();

    // lấy doanh số của từng category trong năm
    List<CategorySalesResp> getCategoryTotalSold();

    List<Category> getAllCategoriess();
    Category getCategoryById(Long id); // Phương thức lấy danh mục theo id
    void saveCategory(Category category); // Phương thức lưu danh mục

    CategoryProductResponse getCategoryWithProducts(Long categoryId);

    CategoryProductPagingResponse getCategoryWithProductsPaging(Long categoryId, Pageable pageable);

    Integer countProducts(Long categoryId);

    void deleteCategory(Long id);

    Page<Category> searchCategory(String search, Pageable pageable);

    Page<Category> findAll(Pageable pageable);
}
