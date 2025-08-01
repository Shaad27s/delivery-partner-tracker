package shaadProject.example.delivery.partner.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shaadProject.example.delivery.partner.app.Module.Order;
import shaadProject.example.delivery.partner.app.Module.OrderStatus;
import shaadProject.example.delivery.partner.app.Module.User;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByCustomer(User customer);
    List<Order> findByDeliveryPartnerIdAndStatus(Long deliveryPartnerId, OrderStatus status);

    List<Order> findByDeliveryPartnerIdAndStatusNot(Long deliveryPartnerId, OrderStatus status);

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByDeliveryPartnerId(Long deliveryPartnerId);




}
