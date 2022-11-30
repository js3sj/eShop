package lt.codeacademy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.codeacademy.entities.Basket;
import lt.codeacademy.repositiries.BasketRepository;

@Service
public class BasketService {

	@Autowired
	BasketRepository basketRepository;
	
	public Basket save(Basket basket) {
		return basketRepository.save(basket);
	}

	public void delete(Basket basket) {
		basketRepository.delete(basket);
	}

	public void deleteById(int id) {
		basketRepository.deleteById(id);
	}

	public List<Basket> getAll(){
		return basketRepository.findAll();
	}
	
    public Optional<Basket> findById (int id) {
    	//basketRepository.findAllById(null)
    	return basketRepository.findById(id);
    }

}
