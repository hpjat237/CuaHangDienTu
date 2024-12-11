package vn.dodientu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.dodientu.model.Product;
import vn.dodientu.service.interfaces.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    
    @GetMapping()
    public String showProductPage() {
        
        return "seller/product-list";  
    }

    @GetMapping("/list")
	public String listProduct(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "seller/product/list";
	}

	@GetMapping("/{id}")
	public String getProductById(@PathVariable Long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "product-detail";
	}
	
	@GetMapping("/add")
	public String createProductPage(Model model) {
		model.addAttribute("product", new Product());
		return "seller/product/form";
	}

	@PostMapping("/add")
	public String createProduct(@ModelAttribute Product product) {
		productService.createProduct(product);
		return "redirect:/product/list";
	}

	@GetMapping("/update/{id}")
	public String updateProductPage(@PathVariable Long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "seller/product/form";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable Long id,@ModelAttribute Product product) {
		productService.updateProduct(id, product);
		return "redirect:/seller/product/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "redirect:/seller/product/list";
	}
    
    
}
