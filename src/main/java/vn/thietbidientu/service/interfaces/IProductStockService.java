package vn.thietbidientu.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.thietbidientu.entity.Product;
import vn.thietbidientu.entity.ProductStock;

import java.util.List;

@Service
public interface IProductStockService {

    List<ProductStock> getAllProductStocks();

    ProductStock updateProductStock(Long productId, Long addedQuantity);

    ProductStock addNewProductStock(Product product, Long initialQuantity);

    Page<ProductStock> getPaginatedProductStocks(Pageable pageable);

    Page<ProductStock> searchProductStocks(String searchTerm, Pageable pageable);
}
