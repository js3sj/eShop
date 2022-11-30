package lt.codeacademy.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lt.codeacademy.dto.ItemDTO;
import lt.codeacademy.entities.*;
import lt.codeacademy.services.*;

@Controller
@RequestMapping(path = "/basket")
public class BasketController {

	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	OrderService orderService;

	List<ItemDTO> itemsDTOList = new ArrayList<ItemDTO>();
	int orderId;
	double totalAmount;
	
	@GetMapping("/find/{id}")
	public String findItemList(@PathVariable("id") int orderId, Model model){
		itemsDTOList.clear();
		List<Basket> besketList = basketService.getAll();
		totalAmount = orderService.findById(orderId).getTotalAmount();
		for (Basket bsk : besketList)
			if (bsk.getOrder_id() == orderId) {
				ItemDTO itmDTO = new ItemDTO(itemService.findById(bsk.getItem_id()));
				itmDTO.setBasketID(bsk.getId());
				itmDTO.setOrderID(orderId);
				itemsDTOList.add(itmDTO);
			}
			
		return "redirect:/basket/index/";
	}
	
	@GetMapping("/index")
	public String showBasketList(Model model){
		model.addAttribute("itemsDTOList", itemsDTOList);
		model.addAttribute("updateTotalAmount", totalAmount);
		return "/basket/index";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") int basketID, Model model) {
		int orderId = 0;
		double itemPrice = 0;	
		Basket basket = new Basket();
		List<Basket> besketList = basketService.getAll();
		
		for (Basket bsk : besketList)
			if (bsk.getId() == basketID)
				basket = bsk;
		
		orderId = basket.getOrder_id();
		basketService.deleteById(basketID);
		logger.warn("Basket with Id: " + basketID + " has been DELETED.");
		
		//Sumazina TotalAmount ir item list
		List<ItemDTO> newItemDTOList = new ArrayList<ItemDTO>();
		for (ItemDTO itm : itemsDTOList)
			if (itm.getBasketID() == basketID)
				itemPrice = itm.getPrice();
			else
				newItemDTOList.add(itm);
		
		itemsDTOList = newItemDTOList;
		Order ord = orderService.findById(orderId);
		ord.setTotalAmount(ord.getTotalAmount()-itemPrice);
		orderService.saveOrder(ord);
		totalAmount -= itemPrice;
		if (itemsDTOList.size() == 0) {
			orderService.deleteById(orderId);
			return "redirect:/orders/index";
		}
		
	    return "redirect:/basket/index";
	}
}
