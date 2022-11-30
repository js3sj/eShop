package lt.codeacademy.repositiries;

import lt.codeacademy.entities.User;
import lt.codeacademy.entities.UsersRoles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Integer>{// gali reiketi i LONG keisti
	//Optional<UsersRoles> findById(long id);
}
