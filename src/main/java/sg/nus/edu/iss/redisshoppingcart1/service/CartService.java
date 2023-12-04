package sg.nus.edu.iss.redisshoppingcart1.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.redisshoppingcart1.model.Item;
import sg.nus.edu.iss.redisshoppingcart1.repo.CartRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepo;
    
    public void saveCart(String name, List<Item> cartWeb) {
        cartRepo.deleteCart(name);
        cartRepo.addCart(name, cartWeb);
    }

    public List<Item> getCart(String name) {
        if (cartRepo.hasCart(name)) {
            return cartRepo.getCart(name);
        } else {
            return new LinkedList<>();
        }
    }

    public void deleteCart(String name) {
        cartRepo.deleteCart(name);
    }
}
