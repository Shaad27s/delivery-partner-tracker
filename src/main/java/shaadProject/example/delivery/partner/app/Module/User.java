package shaadProject.example.delivery.partner.app.Module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity

public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   String name;
   @Column(unique = true,nullable = false)
   String email;
   @Column(nullable = false)
   String password;
   @Enumerated(EnumType.STRING)
   @Column(length = 20, nullable = false, columnDefinition = "ENUM('CUSTOMER','DELIVERY','ADMIN')")
   private  Role role;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Order> ordersPlaced;

    // Orders assigned to this user (if DELIVERY)
    @OneToMany(mappedBy = "deliveryPartner")
    private List<Order> deliveriesAssigned;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrdersPlaced() {
        return ordersPlaced;
    }

    public void setOrdersPlaced(List<Order> ordersPlaced) {
        this.ordersPlaced = ordersPlaced;
    }

    public List<Order> getDeliveriesAssigned() {
        return deliveriesAssigned;
    }

    public void setDeliveriesAssigned(List<Order> deliveriesAssigned) {
        this.deliveriesAssigned = deliveriesAssigned;
    }
}
