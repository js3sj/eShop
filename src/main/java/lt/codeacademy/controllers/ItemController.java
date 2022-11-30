package lt.codeacademy.controllers;

import lt.codeacademy.dto.ItemDTO;
import lt.codeacademy.entities.*;
import lt.codeacademy.services.*;
import lt.codeacademy.utils.FileUploadUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/items")
public class ItemController {

	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	@Autowired
	ItemService itemService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	SessionUserService sessionUserService;
	
	List<Item> items = new ArrayList<Item>();
	List<ItemDTO> itemsDTOList = new ArrayList<ItemDTO>();
	List<ItemDTO> itemsDTOListForbasket = new ArrayList<ItemDTO>();
	double totalAmount = 0;
	int orderID = 0;
	
	@GetMapping("/create")
	public String showCreateForm(Item item) {
		
		return "/items/add-item";
	}

	@PostMapping("/save")
	public String saveItem(@Valid Item item, BindingResult bindingResult,
            @RequestParam("image") MultipartFile multipartFile) {
		if (bindingResult.hasErrors()) {
			return "/items/add-item";
		}
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        item.setPhotos(fileName);
		itemService.save(item);
        String uploadDir = "item-photos/" + item.getId();
        try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("item: " + item + " has been created.");
		
		return "redirect:/items/index";
	}
		
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Item item = itemService.findById(id);
	    model.addAttribute("item", item);
	    return "/items/update-item";
	}
	
	@PostMapping("/update/{id}")
	public String updateItem(@PathVariable("id") int id, @Valid Item item, BindingResult bindingResult,  
			 @RequestParam("image") MultipartFile multipartFile) {
	    if (bindingResult.hasErrors()) {
	        item.setId(id);
	        return "/items/update-item";
	    }
	    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	    String uploadDir = "item-photos/" + item.getId();
	    if (fileName.length() > 0) {
	    	item.setPhotos(fileName);
	    	try {
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else
	    	item.setPhotos(itemService.findById(id).getPhotos());
	    itemService.save(item);
        
	    return "redirect:/items/index";
	}
	    
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") int id, Model model) {
	    Item item = itemService.findById(id);
	    double itemPrice = item.getPrice();
	    itemService.delete(item);
	    logger.warn("Item: " + item + " has been DELTETED.");
	    //Istrina is Basket esancias prekes
	    List<Basket> basketList = basketService.getAll();
	    for (Basket bsk : basketList)
	    	if (bsk.getItem_id() == id) {
	    		//sumazina total mount
	    		Order ord = orderService.findById(bsk.getOrder_id());
	    		ord.setTotalAmount(ord.getTotalAmount()-itemPrice);
	    		orderService.saveOrder(ord);
	    		basketService.deleteById(bsk.getId());
	    	}
	    
	    return "redirect:/items/index";
	}
		
	@GetMapping("/index")
	public String showItemList(Model model){
		itemsDTOList.clear();
		for (Item itm : itemService.getAll())
			itemsDTOList.add(new ItemDTO(itm));
		model.addAttribute("items", itemsDTOList);
		model.addAttribute("updateTotalAmount", totalAmount);
		
		return "/items/index";
	}
	
	@GetMapping("/index/{id}")
	public String showItemListForUpdate(@PathVariable("id") int id, Model model){
		orderID = id;
		items.clear();
		model.addAttribute("items", itemService.getAll());
		model.addAttribute("updateTotalAmount", totalAmount);
		
		return "/items/index";
	}
	
	@GetMapping("/view")
	public String showItemsInThebasket(Model model){
		itemsDTOListForbasket.clear();
		for (Item itm : items)
			itemsDTOListForbasket.add(new ItemDTO(itm));
		model.addAttribute("items", itemsDTOListForbasket);
		model.addAttribute("greeting", "Your added items in the basket");
		model.addAttribute("updateTotalAmount", totalAmount);
		
		return "/items/view-items";
	}

	@GetMapping("/empty")
	public String addToBasket() {
	   	items.clear();
	   	itemsDTOListForbasket.clear();
	    totalAmount = 0;
	    
	   	return "redirect:/items/index";
	}
	
	@GetMapping("/addToBasket/{id}")
	public String addToBasket(@PathVariable("id") int id, Model model) {
	    Item item = itemService.findById(id);
	    totalAmount += item.getPrice();
	    items.add(item);
	    for (Item itm : items)
	    	logger.info("Item: " + itm + " is added to the basket.");
		
	    return "redirect:/items/index";
	}
	
	@GetMapping("/deleteFromBasket/{id}")
	public String deleteFromBasket(@PathVariable("id") int id, Model model) {
		List<Item> itemsTmpList = new ArrayList<Item>();
	    Item item = itemService.findById(id);
	    totalAmount -= item.getPrice();
	    totalAmount = 0;
	    for (Item itm : items)
	    	if (itm.getId() != id) {
	    		itemsTmpList.add(itm);
	    		totalAmount += itm.getPrice();
	    	}
	    items.clear();
	    items = itemsTmpList;
	    logger.info("Item: " + item + " is deleted from the basket.");
		
	    return "redirect:/items/view";
	}
	
	@GetMapping("/buy")
	public String buyAddedItems(Model model) {
		var userList = sessionUserService.getAll();
		long userId = userList.get(userList.size()-1).getUser_id();
		if (orderID > 0){// jei papildomas esamas krepselis
			Order order = orderService.findById(orderID);
			order.setTotalAmount((order.getTotalAmount()+totalAmount));
			orderService.saveOrder(order);
			for (Item itm: items)
				basketService.save(new Basket(orderID, itm.getId()));
		}else{// jei kuriamas naujas krepselis
			Order order = new Order(userId, totalAmount);
			orderService.saveOrder(order);
			for (Item itm: items)
				basketService.save(new Basket(order.getId(), itm.getId()));
		}
		orderID = 0;
		totalAmount = 0;
		items.clear();
		
		return "redirect:/orders/index";
	}
}
