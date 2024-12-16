package vn.thietbidientu.admin;

import vn.thietbidientu.entity.Category;
import vn.thietbidientu.service.impl.CategoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategoryList(@RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "7") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = (search != null && !search.isEmpty())
                ? categoryService.searchCategory(search, pageable)
                : categoryService.findAll(pageable);

        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("search", search);
        return "admin/Categories/categories";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/Categories/addCategory";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin/Categories/editCategory";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/disable/{id}")
    public String disableCategory(@PathVariable Long id) {
        categoryService.disableCategory(id); // Gọi service để vô hiệu danh mục
        return "redirect:/admin/categories"; // Redirect về danh sách
    }

    // Kích hoạt danh mục
    @GetMapping("/activate/{id}")
    public String activateCategory(@PathVariable Long id) {
        categoryService.activateCategory(id); // Gọi service để kích hoạt danh mục
        return "redirect:/admin/categories"; // Redirect về danh sách
    }
}
