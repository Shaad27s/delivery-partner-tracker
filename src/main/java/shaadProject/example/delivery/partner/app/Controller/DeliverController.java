package shaadProject.example.delivery.partner.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shaadProject.example.delivery.partner.app.Module.Order;
import shaadProject.example.delivery.partner.app.service.DeliveryService;
import shaadProject.example.delivery.partner.app.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliverController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeliveryService deliveryService;

    @PreAuthorize("hasRole('DELIVERY')")
    @GetMapping("/assigned-orders/{deliveryPartnerId}")
    public ResponseEntity<List<Order>>assignedOrder(@PathVariable("deliveryPartnerID") Long deliveryPartnerID){
        List<Order> assignedOrder = deliveryService.allAssignedOrder(deliveryPartnerID);
        return  ResponseEntity.ok(assignedOrder);
    }
    @PreAuthorize("hasRole('DELIVERY')")
    @PatchMapping("/update-status")
    public ResponseEntity<String> updateOrderStatus(
            @RequestParam Long orderId,
            @RequestParam String status
    ) {
        deliveryService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status updated to " + status);
    }
}
