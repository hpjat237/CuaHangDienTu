package vn.dodientu.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dodientu.model.Product;
import vn.dodientu.repository.ProductRepository;
import vn.dodientu.service.interfaces.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    // Phương thức lưu sản phẩm
    @Override
	public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
