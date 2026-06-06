package com.crm.controller;
 
import com.crm.model.Product;
import com.crm.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
 
    private final ProductService productService;
 
    @GetMapping
    public String list(@RequestParam(required = false) String search, Model model) {
        if (search != null && !search.isBlank()) {
            model.addAttribute("products", productService.search(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("products", productService.getAll());
        }
        model.addAttribute("activePage", "products");
        return "products/list";
    }
 
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("statuses", Product.ProductStatus.values());
        model.addAttribute("activePage", "products");
        return "products/form";
    }
 
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("statuses", Product.ProductStatus.values());
        model.addAttribute("activePage", "products");
        return "products/form";
    }
 
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Product product,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", Product.ProductStatus.values());
            model.addAttribute("activePage", "products");
            return "products/form";
        }
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Mahsulot muvaffaqiyatli saqlandi!");
        return "redirect:/products";
    }
 
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Mahsulot o'chirildi.");
        return "redirect:/products";
    }
}