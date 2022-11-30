package lt.codeacademy.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	long customerID; //suristi su USER
	double totalAmount;
	
	public Order() {}

	public Order(long customerID, double totalAmount) {
		this.customerID = customerID;
		this.totalAmount = totalAmount;
	}

	public Order(int id, long customerID, double totalAmount) {
		this.id = id;
		this.customerID = customerID;
		this.totalAmount = totalAmount;
	}
	
	public Order(Order order) {
		this.id = order.id;
		this.customerID = order.customerID;
		this.totalAmount = order.totalAmount;
	}

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

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerID=" + customerID + /*", items=" + items + */", totalAmount=" + totalAmount
				+ "]";
	}
	
}
