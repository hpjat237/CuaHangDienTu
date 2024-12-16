package vn.dodientu.service.interfaces;

import java.util.List;

import hcmute.edu.vn.entity.ProductColor;


public interface IProductColorService {
	List<ProductColor> findProductColorByIdProduct(Integer idProduct);
	List<String> getAllColor();
	ProductColor saveProductColor(ProductColor productColor);
	boolean deleteProductColor(Integer idProductColor);
}

