package vn.dodientu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.dodientu.model.Product;
import vn.dodientu.service.implementation.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;
    
    @GetMapping("/product")
    public String showProductPage() {
        // Trả về tên của view (product.jsp)
        return "product";  // Spring Boot sẽ tìm product.jsp trong /WEB-INF/jsp/
    }

    // Phương thức thêm sản phẩm
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Lưu sản phẩm mới vào database thông qua service
        Product savedProduct = productService.saveProduct(product);
        
        // Trả về sản phẩm đã lưu cùng với status CREATED
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}
