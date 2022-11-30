package lt.codeacademy.services;

import lt.codeacademy.entities.Item;
import lt.codeacademy.repositiries.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	public void delete(Item item) {
		itemRepository.delete(item);
	}

	public List<Item> getAll(){
		return itemRepository.findAll();
	}
	
    public Item findById (int id) {
    	Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        return item;
    }

	public void buy(Item item) {
		// TODO Auto-generated method stub
		// 
	}
	
}
