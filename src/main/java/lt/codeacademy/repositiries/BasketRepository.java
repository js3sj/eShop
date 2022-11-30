package lt.codeacademy.repositiries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lt.codeacademy.entities.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer>{
	 //Optional<Basket> findByOrder_id(int order_id);
	 //Optional<Basket> findByItem_id(int item_id);
}
