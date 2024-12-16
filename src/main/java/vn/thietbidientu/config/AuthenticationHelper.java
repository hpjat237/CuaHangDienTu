package vn.thietbidientu.config;

import vn.thietbidientu.entity.User;
import vn.thietbidientu.repository.UserRepository;
import vn.thietbidientu.security.UserPrincipal;
import vn.thietbidientu.security.oauth.CustomOAuth2User;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationHelper {
    private final UserRepository userRepository;
    public AuthenticationHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		/*
		 * if (authentication != null) { switch (authentication) { case
		 * UsernamePasswordAuthenticationToken authenticationToken -> { UserPrincipal
		 * userPrincipal = (UserPrincipal) authentication.getPrincipal(); return
		 * userPrincipal.getUserId(); } case OAuth2AuthenticationToken oauthToken -> {
		 * OAuth2User oauthUser = oauthToken.getPrincipal(); if (oauthUser instanceof
		 * CustomOAuth2User customOAuth2User) { return customOAuth2User.getUserId(); }
		 * else { // Trường hợp OAuth2User không phải là CustomOAuth2User, // bạn cần
		 * lấy userId từ attributes của oauthUser return oauthUser.getAttribute("id");
		 * // Hoặc key tương ứng với userId } } case RememberMeAuthenticationToken
		 * rememberMeAuthenticationToken -> { UserPrincipal userPrincipal =
		 * (UserPrincipal) rememberMeAuthenticationToken.getPrincipal(); return
		 * userPrincipal.getUserId(); } default -> { // Xử lý trường hợp không xác định
		 * return null; } } }
		 */
        return null; // Hoặc xử lý trường hợp không tìm thấy userId
    }

    public String getFullname(){
        User user = userRepository.findById(getUserId()).orElse(null);
        if(user == null){
            return "";
        }
        return user.getFullname()!=null?user.getFullname():"";
    }
}