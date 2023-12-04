package sg.nus.edu.iss.redisshoppingcart1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import sg.nus.edu.iss.redisshoppingcart1.model.Item;
import sg.nus.edu.iss.redisshoppingcart1.service.CartService;

@Controller
@RequestMapping("/")
public class NameController {

    @Autowired
    private CartService cartSvc;
    
    @GetMapping("/cart")
    public String getName(@RequestParam String name, Model m, HttpSession sess) {

        sess.setAttribute("name", name);
        m.addAttribute("name", name);
        m.addAttribute("item", new Item());
        m.addAttribute("cart", cartSvc.getCart(name));
        sess.setAttribute("cart", cartSvc.getCart(name));

        return "cart";
    }

}
