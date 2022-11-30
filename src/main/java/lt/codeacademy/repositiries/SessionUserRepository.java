package lt.codeacademy.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lt.codeacademy.entities.SessionUser;

@Repository
public interface SessionUserRepository extends JpaRepository<SessionUser, Integer>{

}
