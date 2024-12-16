package vn.dodientu.service.implementation;

import hcmute.edu.vn.entity.Shop;
import hcmute.edu.vn.entity.User;
import hcmute.edu.vn.repository.ShopRepository;
import hcmute.edu.vn.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class ShopService implements IShopService {
    @Autowired
    private ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public <S extends Shop> S save(S entity) {
        return shopRepository.save(entity);
    }

    @Override
    public Shop createShop(Shop shop, User salesman) {
        shop.setVendor(salesman); // Liên kết người bán với shop
        shop.setSignUpDate(new Date()); // Ngày tạo shop
        return shopRepository.save(shop); // Lưu vào DB
    }


    @Override
    public List<Map<String, Object>> getTopShops(Integer month, Integer year) {
//        List<Object[]> topShops = shopRepository.findTopShopsByMonthOrYear(month, year);
//
//        return topShops.stream().map(data -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id_shop", data[0]);
//            map.put("totalRevenue", data[1]);
//            return map;
//        }).collect(Collectors.toList());
        List<Object[]> topShops = shopRepository.findTopShopsByMonthOrYear(month, year);

        return topShops.stream().map(data -> {
            Map<String, Object> map = new HashMap<>();
            map.put("shopName", data[0]); // Tên shop
            map.put("totalRevenue", data[1]); // Doanh thu
            return map;
        }).collect(Collectors.toList());
    }

//    @Override
//    public List<Map<String, Object>> getTopShopsByRevenue(String timePeriod) {
//        List<Map<String, Object>> result = new ArrayList<>();
//        List<Object[]> data = new ArrayList<>();
//
//        if ("month".equals(timePeriod)) {
//            data = shopRepository.findTopShopsByMonth();
//        } else if ("year".equals(timePeriod)) {
//            data = shopRepository.findTopShopsByYear();
//        }
//
//        // Xử lý kết quả trả về và thêm thông tin shop
//        for (Object[] row : data) {
//            Map<String, Object> shopData = new HashMap<>();
//            Integer shopId = (Integer) row[0];
//            Double totalRevenue = (Double) row[1];
//
//            // Lấy thông tin shop từ ShopRepository
//            Optional<Shop> shop = shopRepository.findById(shopId);
//            if (shop.isPresent()) {
//                shopData.put("shopName", shop.get().getName());
//            }
//
//            shopData.put("shopId", shopId);
//            shopData.put("totalRevenue", totalRevenue);
//            result.add(shopData);
//        }
//        return result;
//    }

	@Override
	public List<Shop> findAllShop() {
		// TODO Auto-generated method stub
		return this.shopRepository.findAll();
	}

	@Override
	public Shop findShopById(Integer id_shop) {
		// TODO Auto-generated method stub
		return this.shopRepository.getReferenceById(id_shop);
	}

    @Override
    public Shop findShopByIdUser(Integer id_user)
    {
        return this.shopRepository.findShopByUserId(id_user);
    }

	@Override
	public Page<Shop> findShopPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.shopRepository.findAll(pageable);
	}

	@Override
	public Page<Shop> findShopByNameContaining(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return this.shopRepository.findShopByNameContaining(name, pageable);
	}
}
