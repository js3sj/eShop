package lt.codeacademy.services;

import lt.codeacademy.entities.Item;
import lt.codeacademy.entities.User;
import lt.codeacademy.entities.UsersRoles;
import lt.codeacademy.repositiries.ItemRepository;
import lt.codeacademy.repositiries.UserRepository;
import lt.codeacademy.repositiries.UsersRolesRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRolesService {

	@Autowired
	UsersRolesRepository usersRolesRepository;
	
	public UsersRoles save(UsersRoles usersRoles) {
		return usersRolesRepository.save(usersRoles);
	}

	public void delete(UsersRoles usersRoles) {
		usersRolesRepository.delete(usersRoles);
	}

	public List<UsersRoles> getAll(){
		return usersRolesRepository.findAll();
	}
	
    public UsersRoles findById (int id) {
    	UsersRoles usersRoles = usersRolesRepository.findById(null)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        return usersRoles;
    }

}
