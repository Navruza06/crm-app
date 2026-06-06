package com.crm.controller;
 
import com.crm.model.Customer;
import com.crm.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
 
    private final CustomerService customerService;
 
    @GetMapping
    public String list(@RequestParam(required = false) String search, Model model) {
        if (search != null && !search.isBlank()) {
            model.addAttribute("customers", customerService.search(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("customers", customerService.getAllCustomers());
        }
        model.addAttribute("activePage", "customers");
        return "customers/list";
    }
 
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("statuses", Customer.CustomerStatus.values());
        model.addAttribute("activePage", "customers");
        return "customers/form";
    }
 
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        model.addAttribute("statuses", Customer.CustomerStatus.values());
        model.addAttribute("activePage", "customers");
        return "customers/form";
    }
 
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Customer customer,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", Customer.CustomerStatus.values());
            model.addAttribute("activePage", "customers");
            return "customers/form";
        }
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Mijoz muvaffaqiyatli saqlandi!");
        return "redirect:/customers";
    }
 
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Mijoz o'chirildi.");
        return "redirect:/customers";
    }
}