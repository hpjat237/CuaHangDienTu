package vn.thietbidientu.service.interfaces;

import vn.thietbidientu.entity.Cart;
import vn.thietbidientu.entity.CartItem;
import vn.thietbidientu.entity.Customer;
import vn.thietbidientu.payload.request.AddProductToCartRequest;
import vn.thietbidientu.payload.request.UpdateCartReq;

import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ICartService {

    CartItem getCartItemById(Long id);

    void addProductToCart(AddProductToCartRequest addProductToCartRequest);

    Cart getCartByUserId(Long userId);

    void addToCart(Long userId, AddProductToCartRequest addProductToCartRequest);

    void updateProductQuantityInCart(Long userId, UpdateCartReq updateCartReq);

    void removeFromCart(Long userId, Long cartItemId);

    Long countProductInCart();

    Optional<Cart> findCartByCustomer(Customer customer);
}
