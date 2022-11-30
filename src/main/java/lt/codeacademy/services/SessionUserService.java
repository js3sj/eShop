package lt.codeacademy.services;

import lt.codeacademy.entities.SessionUser;
import lt.codeacademy.repositiries.SessionUserRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionUserService {

	@Autowired
	SessionUserRepository sessionUserRepository;
	
	public SessionUser save(SessionUser sessionUser) {
		return sessionUserRepository.save(sessionUser);
	}

	public void delete(SessionUser sessionUser) {
		sessionUserRepository.delete(sessionUser);
	}

	public List<SessionUser> getAll(){
		return sessionUserRepository.findAll();
	}
	
    public SessionUser findById (int id) {
    	SessionUser sessionUser = sessionUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        return sessionUser;
    }
    
    //
}
