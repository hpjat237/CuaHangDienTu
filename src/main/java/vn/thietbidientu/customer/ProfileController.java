package vn.thietbidientu.customer;

import vn.thietbidientu.config.AuthenticationHelper;
import vn.thietbidientu.entity.Address;
import vn.thietbidientu.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer/profile")
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationHelper authenticationHelper;

//    @ResponseBody
//    @GetMapping("/addresses")
//    public ResponseEntity<?> showAddresses() {
//        Long customerId=authenticationHelper.getUserId();
//        return new ResponseEntity<>(userService.getAddresses(customerId), HttpStatus.OK);
//    }

}
