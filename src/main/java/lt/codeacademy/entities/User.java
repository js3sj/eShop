package lt.codeacademy.entities;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;
 
@Entity
@Table(name = "users")
public class User {
 
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotEmpty(message = "Username cannot be empty.")
    @Size(min=1, max=45)
    private String username;
    
    @NotEmpty(message = "User's password cannot be empty.")
    @Size(min=1, max=64)
    private String password;
    
    private boolean enabled;
    
    @NotEmpty(message = "User's name cannot be empty.")
    @Size(min=1, max=45)
    private String name;
    
    @NotEmpty(message = "User's surname cannot be empty.")
    @Size(min=1, max=45)
    private String surname;
    
    @NotEmpty(message = "User's address cannot be empty.")
    @Size(min=1, max=80)
    private String address;
     
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
 
    public User() {}

	public User(Long id, String username, String password, boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String name, String surname, String address) {
		this.name = name;
		this.surname = surname;
		this.address = address;
	}

	public User(Long id, String username, String password, boolean enabled, String name, String surname, String address) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.surname = surname;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	};
   	
	public Long getId() {
        return id;
    }
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", name=" + name + ", surname=" + surname + ", address=" + address + ", roles=" + roles + "]";
	}
 
}
