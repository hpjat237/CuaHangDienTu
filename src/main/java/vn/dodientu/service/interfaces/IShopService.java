package vn.dodientu.service.interfaces;

import java.util.List;

import hcmute.edu.vn.entity.Shop;
import hcmute.edu.vn.entity.User;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IShopService {
	List<Shop> findAllShop();
    <S extends Shop> S save(S entity);
    Shop createShop(Shop shop, User salesman);

    List<Map<String, Object>> getTopShops(Integer month, Integer year);

    //List<Map<String, Object>> getTopShopsByRevenue(String timePeriod);

 	Shop findShopById(Integer id_shop);

    Shop findShopByIdUser(Integer id_user);
    
    Page<Shop> findShopPageable(Pageable pageable);
    
    Page<Shop> findShopByNameContaining(String name, Pageable pageable);
}
