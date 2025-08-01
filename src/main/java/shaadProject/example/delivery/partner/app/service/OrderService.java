package shaadProject.example.delivery.partner.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shaadProject.example.delivery.partner.app.Module.FoodItem;
import shaadProject.example.delivery.partner.app.Module.Order;
import shaadProject.example.delivery.partner.app.Module.OrderStatus;
import shaadProject.example.delivery.partner.app.Module.User;
import shaadProject.example.delivery.partner.app.Repository.FoodItemRepo;
import shaadProject.example.delivery.partner.app.Repository.OrderRepo;
import shaadProject.example.delivery.partner.app.Repository.UserRepo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private OrderRepo orderRepo;
    public Order placeOrder(Long customerId, List<Long> foodItem) {
        User customer = userRepo.findById(customerId)
                .orElseThrow(()->new RuntimeException("Customer not found"));

        List<FoodItem> item = foodItemRepo.findAllById(foodItem);

        Order order =new Order();
        order.setCustomer(customer);
        order.setItems(item);
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt((LocalDateTime.now()));
        return  orderRepo.save(order);
    }

    public String checkSatus(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(()->new RuntimeException("Order not found"));

        return order.getStatus().toString();
    }

    public List<Order> customerHistory(Long customerId) {
        return  orderRepo.findByCustomerId(customerId);
    }

    public Order assignDeliveryPartner(Long orderId, Long deliveryPartnerId) {
         Order oder = orderRepo.findById(orderId).
                 orElseThrow(()->new RuntimeException("order not found"));

         User deliveryPartner = userRepo.findById(deliveryPartnerId)
                 .orElseThrow(()->new RuntimeException("No delivery partner found"));

         oder.setDeliveryPartner(deliveryPartner);
         oder.setStatus(OrderStatus.ASSIGNED);
         return orderRepo.save(oder);
    }

//    public List<Order> allAssignedOrder(Long deliveryPartnerID) {
//        User deliverPartner = userRepo.findById(deliveryPartnerID).
//                orElseThrow(() -> new RuntimeException("Delivery partner not found"));
//
//        if(!deliverPartner.getRole().toString().equals("DELIVERY")){
//            throw  new RuntimeException("User  is not delivery partner");
//        }
//        return orderRepo.findByDeliveryPartnerId(deliveryPartnerID);
//
//    }
}
