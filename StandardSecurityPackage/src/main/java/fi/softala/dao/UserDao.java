package fi.softala.dao;

import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import fi.softala.bean.User;

@Component
public class UserDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	//tallennetaan käyttäjän tiedot tietokantaan
	public void saveUser(User user) {
		String sql1 = "INSERT INTO users(username, password) VALUES (?, ?)";
	    String sql2 = "INSERT INTO user_roles(username, role) VALUES (?, ?)";
		Object[] parameters1 = new Object[] { user.getUsername(), user.getPassword() };
		Object[] parameters2 = new Object[] { user.getUsername(), user.getRole() }; 
		getJdbcTemplate().update(sql1, parameters1);
		getJdbcTemplate().update(sql2, parameters2);
	}
	
	//tarkastetaan, löytyykö annettu käyttäjänimi tietokannasta
	public boolean searchUser(String username) {
		String sql = "select COUNT(username) FROM users WHERE username = ?";
		Object[] parameters = new Object[] { username };
		int count = 0;
		count = jdbcTemplate.queryForObject(sql, parameters, Integer.class);
		if(count == 0){
			return false;
		}
		else return true;

	}
}
