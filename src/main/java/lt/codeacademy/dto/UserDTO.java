package lt.codeacademy.dto;

import java.util.ArrayList;
import java.util.List;
import lt.codeacademy.entities.User;

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String name;
    private String surname;
    private String address;

	List<UserDTO> usersDTOList = new ArrayList<UserDTO>();

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
	}

	public UserDTO(Long id, String username, String password, boolean enabled, String name, String surname,
			String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.surname = surname;
		this.address = address;
	}

	public UserDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<UserDTO> getUsersDTOList() {
		return usersDTOList;
	}

	public void setUsersDTOList(List<UserDTO> usersDTOList) {
		this.usersDTOList = usersDTOList;
	}
	
}
