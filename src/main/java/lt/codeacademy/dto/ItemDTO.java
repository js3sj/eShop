package lt.codeacademy.dto;

import java.util.ArrayList;
import java.util.List;

import lt.codeacademy.entities.Item;

public class ItemDTO {
	
	int basketID;
	int orderID;
	int id;
	int itemID;
	String name;
	String description;
	double price;
	String photosImagePath;
	
	List<ItemDTO> itemsDTOList = new ArrayList<ItemDTO>();
	
	public ItemDTO(Item item) {
		this.itemID = item.getId();
		this.id = item.getId();
		this.name = item.getName();
		this.description = item.getDescription();
		this.price = item.getPrice();
		this.photosImagePath = item.getPhotosImagePath();
	}
	
	public ItemDTO(int basketID, int orderID, int itemID, String name, String description, double price) {
		this.basketID = basketID;
		this.orderID = orderID;
		this.itemID = itemID;
		this.id = itemID;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public ItemDTO() {}

	public int getBasketID() {
		return basketID;
	}

	public void setBasketID(int basketID) {
		this.basketID = basketID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPhotosImagePath() {
		return photosImagePath;
	}

	public void setPhotosImagePath(String photosImagePath) {
		this.photosImagePath = photosImagePath;
	}

}
