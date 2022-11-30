package lt.codeacademy.repositiries;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import lt.codeacademy.entities.User;
 
public interface UserCudRepository extends CrudRepository<User, Long> {
 
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}