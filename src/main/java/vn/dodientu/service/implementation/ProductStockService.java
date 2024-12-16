package vn.dodientu.service.implementation;

import vn.dodientu.entity.Product;
import vn.dodientu.entity.ProductStock;
import vn.dodientu.repository.ProductStockRepository;
import vn.dodientu.service.interfaces.IProductStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockService implements IProductStockService {
    @Autowired
    ProductStockRepository productStockRepository;

    @Autowired
    ProductService productService;

    @Override
    public List<ProductStock> getAllProductStocks() {
        return productStockRepository.findAll();
    }

    @Override
    public ProductStock updateProductStock(Long productId, Long addedQuantity) {
        ProductStock productStock = productStockRepository.findByProduct_ProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productStock.setQuantity(productStock.getQuantity() + addedQuantity);
        return productStockRepository.save(productStock);
    }

    @Override
    public ProductStock addNewProductStock(Product product, Long initialQuantity) {
        ProductStock productStock = new ProductStock();
        productStock.setProduct(product);
        productStock.setQuantity(initialQuantity);
        return productStockRepository.save(productStock);
    }

    @Override
    public Page<ProductStock> getPaginatedProductStocks(Pageable pageable) {
        return productStockRepository.findAll(pageable);
    }

    @Override
    public Page<ProductStock> searchProductStocks(String searchTerm, Pageable pageable) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            // Nếu không có từ khóa tìm kiếm, trả về toàn bộ danh sách có phân trang
            return productStockRepository.findAll(pageable);
        }
        // Nếu có từ khóa tìm kiếm, thực hiện tìm kiếm
        return productStockRepository.searchByProductNameOrCode(searchTerm, pageable);
    }
}
