package lt.codeacademy.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lt.codeacademy.entities.Item;
import lt.codeacademy.entities.User;
import lt.codeacademy.services.UserService;
import lt.codeacademy.utils.FileUploadUtil;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	UserService userService;
	
	@GetMapping("/index")
	public String showMainPage() {
		//System.out.println("INDEX");
		return "/index";
	}

	@GetMapping("/login")
	public String loginUser() {
		//System.out.println("VARTOTOJO PRISIJUNGIMAS");
		return "/login";//
	}

	@GetMapping("/registration")
	public String showRegistrationForm(User user) {
		//System.out.println("NAUJO VARTOTOJO REGISTRACIJA");
		return "/registration";
	}
	

}
