package lt.codeacademy.entities;

import javax.persistence.*;

@Entity
@Table(name = "session_user")
public class SessionUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	long user_id;
	
	public SessionUser() {}

	public SessionUser(long user_id) {
		this.user_id = user_id;
	}

	public SessionUser(int id, long user_id) {
		this.id = id;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_id=" + user_id + "]";
	}
	
}
