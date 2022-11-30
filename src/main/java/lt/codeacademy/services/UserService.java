package lt.codeacademy.services;

import lt.codeacademy.entities.Item;
import lt.codeacademy.entities.User;
import lt.codeacademy.repositiries.ItemRepository;
import lt.codeacademy.repositiries.UserRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public List<User> getAll(){
		return userRepository.findAll();
	}
	
    public User findById (long id) {
    	User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        return user;
    }

	public long findIdByUserName(String username) {
		return userRepository.findByUsername(username).get().getId(); 
	}
	
	public boolean isAvailableUserName(String username) {
		if (!userRepository.findByUsername(username).isEmpty())
			return true;
		else 
			return false;
	}

}
