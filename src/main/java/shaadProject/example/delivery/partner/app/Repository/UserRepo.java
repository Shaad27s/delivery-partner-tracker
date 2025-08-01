package shaadProject.example.delivery.partner.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shaadProject.example.delivery.partner.app.Module.Role;
import shaadProject.example.delivery.partner.app.Module.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    boolean existsByEmail(String email);

}
