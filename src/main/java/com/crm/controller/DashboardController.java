package com.crm.controller;
 
import com.crm.service.CustomerService;
import com.crm.service.OrderService;
import com.crm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
@RequiredArgsConstructor
public class DashboardController {
 
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ProductService productService;
 
    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalCustomers", customerService.countAll());
        model.addAttribute("activeCustomers", customerService.countActive());
        model.addAttribute("totalOrders", orderService.countAll());
        model.addAttribute("pendingOrders", orderService.countPending());
        model.addAttribute("totalProducts", productService.countAll());
        model.addAttribute("availableProducts", productService.countAvailable());
        model.addAttribute("totalRevenue", orderService.getTotalRevenue());
        model.addAttribute("recentOrders", orderService.getRecentOrders());
        model.addAttribute("activePage", "dashboard");
        return "dashboard";
    }
}