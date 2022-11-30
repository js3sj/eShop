package lt.codeacademy.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.dto.UserDTO;
import lt.codeacademy.entities.User;
import lt.codeacademy.entities.UsersRoles;
import lt.codeacademy.services.SessionUserService;
import lt.codeacademy.services.UserService;
import lt.codeacademy.services.UsersRolesService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	SessionUserService sessionUserService;

	@Autowired
	UserService userService;
	
	@Autowired
	UsersRolesService usersRolesService;

	@PostMapping("/create")
	public String saveUser(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		String username = user.getUsername();
		if (userService.isAvailableUserName(username)) {
			logger.info("User with username: " + username + " already exist.");
			return "redirect:/user/registrationFailed/"+username;
		}
		else {
			user.setPassword(createHash(user.getPassword()));
			user.setEnabled(true);
			userService.save(user);
			int userId = (int) userService.findIdByUserName(username);
			usersRolesService.save(new UsersRoles(userId, 1));
			logger.info("USER with username: " + user/*.getUsername()*/ + " is created.");
			return "redirect:/user/registrationSuccess/"+username;
		}
	}

	@GetMapping("/registrationFailed/{username}")
	public String showRegistrationFailedPage(@PathVariable("username") String username, Model model) {
		model.addAttribute("registration", username);
	    return "/registrationFailed";
	}

	@GetMapping("/registrationSuccess/{username}")
	public String showRegistratinoSuccessPage(@PathVariable("username") String username, Model model) {
		model.addAttribute("registration", username);
	    return "/registrationSuccess";
	}

	public String createHash(String password) {
		var encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@GetMapping("/index")
	public String showAllUsersList(Model model){
		List<UserDTO> usersDTOList = new ArrayList<UserDTO>();
		usersDTOList.clear();
		for (User user : userService.getAll())
			usersDTOList.add(new UserDTO(user));
		model.addAttribute("users", usersDTOList);
		
		return "/user/index";
	}

//	@GetMapping("/delete/{id}")
//	public String deleteUser(@PathVariable("id") long id, Model model) {
//	    User user = userService.findById(id);
//	    userService.delete(user);
//	    UsersRoles userRole = usersRolesService.findById((int)id);
//	    usersRolesService.delete(userRole);
//	    logger.warn("User: " + user + " has been DELTETED.");
//	    
//	    return "redirect:/user/index";
//	}

}
