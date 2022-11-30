package lt.codeacademy.controllers;

import lt.codeacademy.dto.OrderDTO;
import lt.codeacademy.entities.*;
import lt.codeacademy.services.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/orders")
public class OrderController {

	Logger logger = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	BasketService basketService;

	@Autowired
	UserService userService;

	@GetMapping("/index")
	public String showOrders(Model model){
		List<Order> ordersList = orderService.getAll();
		List<OrderDTO> ordersDTOList = new ArrayList<OrderDTO>();
		String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		if (authority.equals("[ADMIN]")) {
			for (Order ord : ordersList) 
				ordersDTOList.add(new OrderDTO(ord, userService.findById(ord.getCustomerID()).getUsername()));

			model.addAttribute("ordersDTO", ordersDTOList);
		}
		else{
			List<Order> tmpOrdersList = new ArrayList<Order>();
			var userId = userService.findIdByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
			for (Order ord : ordersList) {
				if (ord.getCustomerID() == userId)
					ordersDTOList.add(new OrderDTO(ord, userService.findById(ord.getCustomerID()).getUsername()));
			}

			model.addAttribute("ordersDTO", ordersDTOList);
		}

		return "/orders/index";
	}

	@GetMapping("/delete/{id}")
	public String deleteOrder(@PathVariable("id") int orderID, Model model) {
		List<Basket> basketList = basketService.getAll();
		for (Basket bsk : basketList)
			if (bsk.getOrder_id() == orderID)
			{
				basketService.delete(bsk);
				logger.warn("Basket with Order iD: " + orderID + " is DELETED!");
			}
		orderService.deleteById(orderID);
		logger.warn("Order with Order iD: " + orderID + " is DELETED!");
		return "redirect:/orders/index";
	}

}
