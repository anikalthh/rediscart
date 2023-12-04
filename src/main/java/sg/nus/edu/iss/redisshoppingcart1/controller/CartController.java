package sg.nus.edu.iss.redisshoppingcart1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.nus.edu.iss.redisshoppingcart1.model.Item;
import sg.nus.edu.iss.redisshoppingcart1.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired 
    private CartService cartSvc;
    
    @PostMapping
    public String postCart(@Valid @ModelAttribute Item item, BindingResult bindings, Model m, HttpSession sess) {
        String name = sess.getAttribute("name").toString();
        List<Item> cart = (List<Item>)sess.getAttribute("cart");

        if (bindings.hasErrors()) {
            m.addAttribute("name", name);
            m.addAttribute("cart", cart);
            return "cart";
        }

        cart.add(item);
        m.addAttribute("name", name);
        m.addAttribute("cart", cart);
        sess.setAttribute("cart", cart);

        return "cart";
    }

    @PostMapping("/checkout") 
    public String checkout(HttpSession sess) {
        String name = sess.getAttribute("name").toString();
        List<Item> cart = (List<Item>)sess.getAttribute("cart");
        cartSvc.saveCart(name, cart);
        sess.invalidate();

        return "redirect:/";
    }
}
