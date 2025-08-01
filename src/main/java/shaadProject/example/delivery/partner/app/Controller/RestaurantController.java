package shaadProject.example.delivery.partner.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shaadProject.example.delivery.partner.app.Module.FoodItem;
import shaadProject.example.delivery.partner.app.Module.Restaurant;
import shaadProject.example.delivery.partner.app.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired private RestaurantService restaurantService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{userId}")
    public ResponseEntity<Restaurant> addRestaurant(@PathVariable Long userId,
                                                    @RequestBody Restaurant restaurant){
        Restaurant restaurant1 = restaurantService.addRestaurant(userId,restaurant);
        return ResponseEntity.ok(restaurant1);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{restId}/{userId}/menu")
   public  ResponseEntity<FoodItem> addFood (@PathVariable Long restId,@PathVariable Long userId,
                                             @RequestBody FoodItem foodItem){
        return ResponseEntity.ok(restaurantService.addFoodItem(restId,userId,foodItem));
    }

    @GetMapping("/{restId}/menu")
    public  ResponseEntity<List<FoodItem>>getMenu(@PathVariable Long restId){
        return ResponseEntity.ok(restaurantService.getMenu(restId));

    }
    @GetMapping("/{restId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long restId){
        return  ResponseEntity.ok(restaurantService.restrauntById(restId));
    }
    @GetMapping("/{restId}/menu/{menuId}")
    public ResponseEntity<FoodItem>menu1(@PathVariable Long restId,
                                         @PathVariable Long menuId){
        return  ResponseEntity.ok(restaurantService.menuOne(restId,menuId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam String keyword) {
        return ResponseEntity.ok(restaurantService.searchRestaurants(keyword));
    }

}
