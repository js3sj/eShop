package lt.codeacademy.services;

import lt.codeacademy.entities.Order;
import lt.codeacademy.repositiries.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public void delete(Order order) {
		orderRepository.delete(order);
	}
	
	public void deleteById(int orderId) {
		orderRepository.deleteById(orderId);
	}

	public List<Order> getAll(){
		return orderRepository.findAll();
	}
	
    public Order findById (int id) {
    	Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        return order;
    }
    
    //
}
