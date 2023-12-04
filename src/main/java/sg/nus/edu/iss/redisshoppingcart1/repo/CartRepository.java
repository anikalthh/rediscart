package sg.nus.edu.iss.redisshoppingcart1.repo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.redisshoppingcart1.model.Item;

@Repository
public class CartRepository {
    
    @Autowired @Qualifier("myredis")
    private RedisTemplate<String, String> template;

    public Boolean hasCart(String name) {
        return template.hasKey(name);
    }
    
    public List<Item> getCart(String name) {
        ListOperations<String, String> cart = template.opsForList();
        Long size = cart.size(name);
        List<Item> cartWeb = new LinkedList<>();

        for (String i: cart.range(name, 0, size)) {
            String[] KVpair = i.split(",");
            Item newItem = new Item();
            newItem.setName(KVpair[0]);
            newItem.setQuantity(Integer.parseInt(KVpair[1]));
            cartWeb.add(newItem);
        }

        return cartWeb;
    }

    public void addCart(String name, List<Item> cartWeb) {
        ListOperations<String, String> cart = template.opsForList();
        for (Item item: cartWeb) {
            cart.rightPush(name, item.getName()+","+item.getQuantity());
        }
    }

    public void deleteCart(String name) {
        template.delete(name);
    }
}
