package vn.dodientu.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import hcmute.edu.vn.entity.ProductColor;
import hcmute.edu.vn.repository.ProductColorRepository;
import hcmute.edu.vn.service.IProductColorService;


@Service
public class ProductColorService implements IProductColorService{
	
	private final ProductColorRepository productColorRepository;
	
	public ProductColorService(ProductColorRepository productColorRepository) {
		this.productColorRepository = productColorRepository;
	}

	@Override
	public List<ProductColor> findProductColorByIdProduct(Integer idProduct) {
		// TODO Auto-generated method stub
		return this.productColorRepository.findProductColorByIdProduct(idProduct);
	}

	@Override
	public ProductColor saveProductColor(ProductColor productColor) {
		// TODO Auto-generated method stub
		return this.productColorRepository.save(productColor);
	}

	@Override
	public boolean deleteProductColor(Integer idProductColor) {
		// TODO Auto-generated method stub
		if (this.productColorRepository.existsById(idProductColor)) {
			this.productColorRepository.deleteById(idProductColor);
			return true;
		}
		return false;
	}

	@Override
	public List<String> getAllColor() {
		// TODO Auto-generated method stub
		return this.productColorRepository.getAllColor();
	}

}