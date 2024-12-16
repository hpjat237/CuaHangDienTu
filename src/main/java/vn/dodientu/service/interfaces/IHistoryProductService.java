package vn.dodientu.service.interfaces;

import hcmute.edu.vn.entity.Product;
import hcmute.edu.vn.entity.User;

import java.util.List;

public interface IHistoryProductService {
    List<Product> getProductsViewedByUser(User user);

}
