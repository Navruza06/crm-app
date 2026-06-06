package com.crm.controller;
 
import com.crm.model.Order;
import com.crm.service.CustomerService;
import com.crm.service.OrderService;
import com.crm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
 
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;
 
    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("activePage", "orders");
        return "orders/list";
    }
 
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("statuses", Order.OrderStatus.values());
        model.addAttribute("activePage", "orders");
        return "orders/form";
    }
 
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getById(id));
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("statuses", Order.OrderStatus.values());
        model.addAttribute("activePage", "orders");
        return "orders/form";
    }
 
    @PostMapping("/save")
    public String save(@ModelAttribute Order order, RedirectAttributes redirectAttributes) {
        orderService.save(order);
        redirectAttributes.addFlashAttribute("success", "Buyurtma saqlandi!");
        return "redirect:/orders";
    }
 
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        orderService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Buyurtma o'chirildi.");
        return "redirect:/orders";
    }
}