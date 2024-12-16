package vn.thietbidientu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.thietbidientu.util.Logger;

import java.security.Principal;

@RestController
public class TestRememberme {
    @GetMapping("/auth/tt")
    public String test(Principal principal) {
        Logger.log("Principal: " + principal);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Logger.log("Authentication: " + authentication);
        return "test";
    }
}
