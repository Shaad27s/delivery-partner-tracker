package shaadProject.example.delivery.partner.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shaadProject.example.delivery.partner.app.Module.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findByNameContainingIgnoreCase(String keyword);

}
