package fi.softala.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Size;

public class User {
	
	@NotEmpty 
    private String username;
	
	@NotEmpty @Size(min=3, max=60)
    private String password;
	
	@NotEmpty @Size(min=3, max=60)
    private String confirmPassword;
	
	@NotEmpty
	private String role;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;	
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
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		this.password = hashedPassword;
	}
	
	public void setEmptyPassword(String password) {
		this.password = password;
	}
		
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "<br> Käyttäjänimi: " + username + "<br> Hashattu salasana: " + password
				+ "<br> rooli: " + role;
	}
}
