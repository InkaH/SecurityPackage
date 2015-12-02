package fi.softala.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {
	
	@NotEmpty (message = "Anna email-osoite.") @Email (message = "Anna email-osoite oikeassa muodossa.")
    private String username;
	
	//salasanaan on tässä hyödytöntä laittaa tyhjän tai minimi/maksimi-tarkastusta, koska salasanojen jQuery-vertailuskripti estää tyhjän salasanan lähetyksen 
	//ja salasanan hashaaja muuttaa salasanan jokatapauksessa 60-merkkiseksi hashiksi ennen validointitapahtumaa. Salasanan pituus tarkastetaan frontendissä jQuerylla.
    private String password;
	
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
	
	//tarvitaan metodia, jolla pystytään asettamaan tyhjä salasana
	//formiin, mikäli formissa ilmeni jokin virhe sen lähettämisen jälkeen
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
