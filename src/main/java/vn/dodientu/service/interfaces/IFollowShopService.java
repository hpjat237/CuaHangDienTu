package vn.dodientu.service.interfaces;

import hcmute.edu.vn.entity.FollowShop;
import hcmute.edu.vn.entity.Shop;
import hcmute.edu.vn.entity.User;

import java.util.List;

public interface IFollowShopService {
    boolean isFollowed(Shop shop, User user);

    List<FollowShop> getShopsFollowedByUser(Integer userId);
}
