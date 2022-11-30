package lt.codeacademy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lt.codeacademy.controllers.BasketController;
import lt.codeacademy.controllers.ItemController;
import lt.codeacademy.controllers.LoggingController;
import lt.codeacademy.controllers.MainController;
import lt.codeacademy.controllers.OrderController;
import lt.codeacademy.controllers.UserController;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class eshopApplicationTests {
	
	@Autowired
	ItemController itemController;

	@Autowired
	MainController mainController;
	
	@Autowired
	BasketController basketController;

	@Autowired
	LoggingController loggingController;

	@Autowired
	OrderController orderController;

	@Autowired
	UserController userController;

	@Test
	void itemControllerLoads() {
		Assertions.assertThat(itemController).isNotNull();
	}

	@Test
	void mainControllerLoads() {
		Assertions.assertThat(mainController).isNotNull();
	}
	
	@Test
	void loggingControllerLoads() {
		Assertions.assertThat(loggingController).isNotNull();
	}

	@Test
	void basketControllerLoads() {
		Assertions.assertThat(basketController).isNotNull();
	}

	@Test
	void orderControllerLoads() {
		Assertions.assertThat(orderController).isNotNull();
	}

	@Test
	void userControllerLoads() {
		Assertions.assertThat(userController).isNotNull();
	}
	
}
