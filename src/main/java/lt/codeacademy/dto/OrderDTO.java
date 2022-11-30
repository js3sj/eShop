package lt.codeacademy.dto;

import java.util.ArrayList;
import java.util.List;

import lt.codeacademy.entities.Order;
import lt.codeacademy.entities.User;

public class OrderDTO {

    private int id;
	long customerID; //suristi su USER
	double totalAmount;
    private String username;

	List<OrderDTO> ordersDTOList = new ArrayList<OrderDTO>();

	public OrderDTO(Order order, String username) {
		this.id = order.getId();
		this.customerID = order.getCustomerID();
		this.totalAmount = order.getTotalAmount();
		this.username = username;
	}
	
	public OrderDTO(int id, long customerID, double totalAmount) {
		this.id = id;
		this.customerID = customerID;
		this.totalAmount = totalAmount;
	}
	
	public OrderDTO(int id, long customerID, double totalAmount, String username, List<OrderDTO> ordersDTOList) {
		this.id = id;
		this.customerID = customerID;
		this.totalAmount = totalAmount;
		this.username = username;
		this.ordersDTOList = ordersDTOList;
	}

	public OrderDTO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<OrderDTO> getOrdersDTOList() {
		return ordersDTOList;
	}

	public void setOrdersDTOList(List<OrderDTO> ordersDTOList) {
		this.ordersDTOList = ordersDTOList;
	}
	
}
