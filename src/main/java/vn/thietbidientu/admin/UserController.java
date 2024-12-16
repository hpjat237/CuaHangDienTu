package vn.thietbidientu.admin;

import jakarta.validation.Valid;
import vn.thietbidientu.dto.AddUserDTO;
import vn.thietbidientu.dto.UserDTO;
import vn.thietbidientu.entity.Role;
import vn.thietbidientu.entity.User;
import vn.thietbidientu.image.ImageService;
import vn.thietbidientu.service.impl.RoleService;
import vn.thietbidientu.service.impl.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

// ko có manager
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private ImageService imageService;

    @GetMapping
    public String listUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(value = "search", required = false) String search,
            Model model) {
        int pageSize = 7; // 7 dòng mỗi trang
        Page<User> userPage;

        if (search != null && !search.isEmpty()) {
            userPage = userService.searchUsers(search, page, pageSize);
        } else {
            userPage = userService.getUsers(page, pageSize);
        }

        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("search", search);
        return "admin/user/listUser";
    }


    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findByRoleNames(List.of("MANAGER", "CUSTOMER", "SHIPPER")));
        return "admin/user/createUser";
    }

    @PostMapping("/create")
    public String createUser(
            @ModelAttribute @Valid AddUserDTO userDTO,
            @RequestParam("imagePath") MultipartFile imageFile,
            BindingResult result,
            Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "admin/user/createUser";
        }
        String image = imageFile != null && !imageFile.isEmpty() ? imageService.saveImage(imageFile) : null;
        userDTO.setImage(image);
        userService.createUser(userDTO);
        return "redirect:/admin/user";
    }


    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);

        if (user == null) {
            model.addAttribute("error", "User not found!");
            return "redirect:/admin/user";
        }
        AddUserDTO userDTO = new AddUserDTO();
        BeanUtils.copyProperties(user, userDTO);
        model.addAttribute("user", userDTO);
        model.addAttribute("roles", roleService.findByRoleNames(List.of("MANAGER", "CUSTOMER", "SHIPPER")));
        return "admin/user/editUser";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute @Valid AddUserDTO userDTO,
                           @RequestParam("imagePath") MultipartFile imageFile, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "admin/user/editUser";
        }

        if (imageFile == null || imageFile.isEmpty()) {
            String existingImage = userService.getExistingImage(id);
            userDTO.setImage(existingImage);
        } else {
            // Save new image
            String newImageUrl = imageService.saveImage(imageFile);
            userDTO.setImage(newImageUrl);
        }

        userService.saveUser(userDTO);
        return "redirect:/admin/user";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            model.addAttribute("error", "Unable to delete user. They may be referenced elsewhere.");
        }
        return "redirect:/admin/user";
    }
}
