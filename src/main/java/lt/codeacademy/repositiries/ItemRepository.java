package lt.codeacademy.repositiries;

import lt.codeacademy.entities.Item;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	Optional<Item> findByName(String name);
}
