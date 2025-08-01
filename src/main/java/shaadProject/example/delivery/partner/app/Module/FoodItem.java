package shaadProject.example.delivery.partner.app.Module;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class FoodItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  Long id;

   @Column
   String name;
   @Column
   String price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // FK in food_item table
    @JsonBackReference
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
