package sg.nus.edu.iss.redisshoppingcart1.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Item {
    @NotBlank(message = "Item name is a mandatory field.")
    private String name;

    @NotNull(message = "Item quantity is a mandatory field.")
    @Min(value=1, message = "You must get at least 1.")
    @Max(value=10, message = "You cannot order more than 10 of this item.")
    private Integer quantity;
    
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    public Item() {} 
}
