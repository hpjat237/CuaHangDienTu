package vn.dodientu.service.interfaces;

import java.util.List;

import vn.dodientu.model.Product;

public interface IProductService {

	// Phương thức lưu sản phẩm
	Product saveProduct(Product product);
	Product createProduct(Product product);
	Product updateProduct(Long id, Product product);
	void deleteProduct(Long id);
	Product getProductById(Long id);
	List<Product> getAllProducts();
}