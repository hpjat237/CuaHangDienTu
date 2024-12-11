package vn.dodientu.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dodientu.exception.ResourceNotFoundException;
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

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setUpdatedAt(new java.util.Date());

        return productRepository.save(existingProduct);
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
		
	}

	@Override
	public Product getProductById(Long id) {
		 return productRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
    
}
