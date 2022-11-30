package lt.codeacademy.repositiries;

import lt.codeacademy.entities.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	Optional<Order> findByCustomerID(int customerID);
}
