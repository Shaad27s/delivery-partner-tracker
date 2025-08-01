package shaadProject.example.delivery.partner.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shaadProject.example.delivery.partner.app.Module.FoodItem;
import shaadProject.example.delivery.partner.app.Module.Restaurant;
import shaadProject.example.delivery.partner.app.Module.Role;
import shaadProject.example.delivery.partner.app.Module.User;
import shaadProject.example.delivery.partner.app.Repository.FoodItemRepo;
import shaadProject.example.delivery.partner.app.Repository.RestaurantRepo;
import shaadProject.example.delivery.partner.app.Repository.UserRepo;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private FoodItemRepo foodItemRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    public Restaurant addRestaurant(Long UserId,Restaurant restaurant) {
        User user= userRepo.findById(UserId).
                orElseThrow(()-> new RuntimeException("User not fond"));

        if(!user.getRole().equals(Role.ADMIN)){
            throw  new RuntimeException("Only Admin can add restaurant");
        }

       return restaurantRepo.save(restaurant);
    }

    public FoodItem addFoodItem(Long restID, Long userID, FoodItem foodItem) {
        Restaurant restaurant = restaurantRepo.findById(restID).orElseThrow(()
                -> new RuntimeException("Restaurant not found"));
        foodItem.setRestaurant(restaurant);
        return  foodItemRepo.save(foodItem);


    }

    public List<FoodItem> getMenu(Long restId) {
        return  foodItemRepo.findByRestaurantId(restId);
    }

    public Restaurant restrauntById(Long restId) {
       return  restaurantRepo.findById(restId).
                orElseThrow(()-> new RuntimeException("restaurant not found with id " +restId));
    }

    public FoodItem menuOne(Long restId, Long menuID) {
        Restaurant rest = restaurantRepo.findById(restId).
                orElseThrow(()-> new RuntimeException("restuarant not found"));
        FoodItem item = foodItemRepo.findById(menuID).
                orElseThrow(()->new RuntimeException("item not found"));
        if(!item.getRestaurant().getId().equals(rest.getId())){
            throw  new RuntimeException("item not found in restuarant");
        }
        return item;
    }
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRepo.findByNameContainingIgnoreCase(keyword);
    }

}


