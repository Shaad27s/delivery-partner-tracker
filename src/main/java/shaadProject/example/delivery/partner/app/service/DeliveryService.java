package shaadProject.example.delivery.partner.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import shaadProject.example.delivery.partner.app.Module.Order;
import shaadProject.example.delivery.partner.app.Module.OrderStatus;
import shaadProject.example.delivery.partner.app.Module.User;
import shaadProject.example.delivery.partner.app.Repository.OrderRepo;
import shaadProject.example.delivery.partner.app.Repository.UserRepo;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;


    public List<Order> allAssignedOrder(Long deliveryPartnerID) {
        User deliverPartner = userRepo.findById(deliveryPartnerID).
                orElseThrow(() -> new RuntimeException("Delivery partner not found"));

        if(!deliverPartner.getRole().toString().equals("DELIVERY")){
            throw  new RuntimeException("User  is not delivery partner");
        }
        return orderRepo.findByDeliveryPartnerId(deliveryPartnerID);

    }

    public void updateOrderStatus(Long orderId, String statusStr) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        try {
            OrderStatus status = OrderStatus.valueOf(statusStr.toUpperCase());
            order.setStatus(status);
            orderRepo.save(order);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + statusStr);
        }
    }

    }

