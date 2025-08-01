package shaadProject.example.delivery.partner.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shaadProject.example.delivery.partner.app.Module.FoodItem;

import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem,Long> {

    List<FoodItem> findByRestaurantId(Long restaurantId);

}
