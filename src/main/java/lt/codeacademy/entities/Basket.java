package lt.codeacademy.entities;

import javax.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	int order_id;
	int item_id;
	
	public Basket() {}

	public Basket(int order_id, int item_id) {
		this.order_id = order_id;
		this.item_id = item_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	@Override
	public String toString() {
		return "Basket [order_id=" + order_id + ", item_id=" + item_id + "]";
	}
	
}
