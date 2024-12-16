package vn.dodientu.controller.admin;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.dodientu.model.User;
import vn.dodientu.service.interfaces.IUserService;

@Controller
@RequestMapping("/admin/customers")
public class AdminCustomerController {
	@Autowired
    private IUserService userService;
    @GetMapping
    public String getUserList(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, Model model) {
    	Page<User> userPage = userService.getUsers(page, size);
    	 System.out.println("Total Elements: " + userPage.getTotalElements());
    	 System.out.println("Page Content: " + userPage.getContent());
    	model.addAttribute("userPage", userPage);
        return "admin/customer-list";
    }

    @GetMapping("/{id}")
    public String index2(@PathVariable int id, Model model) {
        

        return "customer-details"; 
    }
}
