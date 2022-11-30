package lt.codeacademy.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
    @NotEmpty(message = "Item's name cannot be empty.")
    @Size(min=1, max=255)
	String name;
    
    @NotEmpty(message = "Item's description cannot be empty.")
    @Size(min=1, max=255)
	String description;
    
    @NotNull(message = "Item's price cannot be empty.")
    @Min(0)
	double price;
	
	@Column(nullable = true, length = 64)
    String photos;
	
	public Item() {}

	public Item(int id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Item(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Item(int id, String name, String description, double price, String photos) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.photos = photos;
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

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
    @Transient
    public String getPhotosImagePath() {
        if (photos == null) return null;
         
        return "/item-photos/" + id + "/" + photos;
    }

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", photos="
				+ photos + "]";
	}

}
