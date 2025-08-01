package shaadProject.example.delivery.partner.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shaadProject.example.delivery.partner.app.Module.Order;
import shaadProject.example.delivery.partner.app.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OderNewController {
    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(
            @RequestParam Long customerId, @RequestBody List<Long>foodItem){
        Order nWorder = orderService.placeOrder(customerId,foodItem);
        return  ResponseEntity.ok(nWorder);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/status/{orderId}")
    public  ResponseEntity<String>orderStatus(@PathVariable Long orderId){
        String status = orderService.checkSatus(orderId);
        return ResponseEntity.ok(status);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/CustomerHistory/{customerId}")
    public  ResponseEntity<List<Order>>customerHistory(@PathVariable Long customerId){
        return ResponseEntity.ok(orderService.customerHistory(customerId));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/assign/{orderId}")
    public ResponseEntity<Order> assignDeliveryPartner(
            @PathVariable Long orderId,
            @RequestParam Long deliveryPartnerId) {

        Order updatedOrder = orderService.assignDeliveryPartner(orderId, deliveryPartnerId);
        return ResponseEntity.ok(updatedOrder);
    }



}
