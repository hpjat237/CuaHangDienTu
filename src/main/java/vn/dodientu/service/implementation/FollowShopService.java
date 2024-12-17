package vn.dodientu.service.implementation;

import hcmute.edu.vn.entity.FollowShop;
import hcmute.edu.vn.entity.Shop;
import hcmute.edu.vn.entity.User;
import hcmute.edu.vn.repository.FollowShopRepository;
import hcmute.edu.vn.repository.UserRepository;
import hcmute.edu.vn.service.IFollowShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowShopService implements IFollowShopService {
    @Autowired
    private FollowShopRepository followShopRepository;
    @Autowired
    private UserRepository userRepository;

    // Thêm một bản ghi FollowShop
    public void addFollowShop(User user, Shop shop) {
        FollowShop followShop = new FollowShop();
        followShop.setUser(user); // Gán user
        followShop.setShop(shop); // Gán shop

        // Lưu bản ghi FollowShop vào cơ sở dữ liệu
        followShopRepository.save(followShop);
    }


    // Xóa theo user và shop (nếu bạn muốn xóa theo user và shop cụ thể)
    public void deleteFollowShopByUserAndShop(User user, Shop shop) {
        followShopRepository.findAll().stream()
                .filter(f -> f.getUser().equals(user) && f.getShop().equals(shop))
                .findFirst().ifPresent(followShop -> followShopRepository.delete(followShop));

    }

    @Override
    public boolean isFollowed(Shop shop, User user) {
        return followShopRepository.existsByShopAndUser(shop, user);
    }

    @Override
    public List<FollowShop> getShopsFollowedByUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        return followShopRepository.findByUser(user);
    }
}
