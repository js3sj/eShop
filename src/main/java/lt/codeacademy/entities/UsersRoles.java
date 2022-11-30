package lt.codeacademy.entities;

import java.util.*;

import javax.persistence.*;
 
@Entity
@Table(name = "users_roles")
public class UsersRoles {
 
    @Id
    @Column(name = "user_id")
    private int userId;
 
    @Column(name = "role_id")
    private int roleId;
 
    public UsersRoles() {}

	public UsersRoles(int roleId) {
		this.roleId = roleId;
	}

	public UsersRoles(int userId, int roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UsersRoles [userId=" + userId + ", roleId=" + roleId + "]";
	}


}
