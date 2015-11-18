package fi.softala.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fi.softala.bean.User;
import fi.softala.dao.UserDao;

/**
 * Sovelluksen controller.
 * 
 * @author Inka Haltiapuu
 *
 */

@Controller
public class MainController {
	
	@Inject
	private UserDao dao;

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String defaultPage(Model model) {
		return "hello";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(Model model) {
		return "user";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
			return "admin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		if (error != null) {
			model.addAttribute("error", "Virheellinen käyttäjänimi tai salasana.");
		}
		if (logout != null) {
			model.addAttribute("msg", "Olet kirjautunut ulos.");
		}
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String saveUser(Model model, @Valid User user, BindingResult bindingResult){
		 if (bindingResult.hasErrors()) {
			 user.setEmptyPassword("");
	         return "registration";
	        }
		//tarkistetaan ettei kyseiselle käyttäjänimelle ole jo luotu tiliä
		boolean duplicateUsername = getDao().searchUser(user.getUsername());
		if(!duplicateUsername){
			user.setRole("ROLE_USER");
			getDao().saveUser(user);
			model.addAttribute("success", " Rekisteröinti tehty tiedoilla: " + user.toString());
			return "success";
			}
		else {
			//jos nimi on jo käytössä, tyhjätään kentät ja palataan rekisteröitymislomakkeeseen
			model.addAttribute("error", "Antamallasi sähköpostiosoitteella on jo rekisteröidytty palveluun.");
			user.setUsername("");
			user.setEmptyPassword("");
			model.addAttribute("user", user);
			return "registration";
		}
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		// printataan konsolille sisäänkirjautuneen käyttäjän tietoja
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		}
		return "403";
	}
}