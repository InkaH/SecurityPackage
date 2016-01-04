package fi.softala.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	final static Logger logger = LoggerFactory.getLogger(MainController.class);
	
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
	public String userPage(Model model, Principal principal) {
		// printataan konsolille sisäänkirjautuneen käyttäjänimi esimerkin vuoksi
		String name = principal.getName();
		System.out.println("Nimi on: " + name);
		// printataan konsolille sisäänkirjautuneen käyttäjän tietoja esimerkin vuoksi
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println("Sisäänkirjautumistiedot: " + userDetail);
		}
		return "user";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		return "admin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {
		logger.info("kirj.sisään");

		if (error != null) {
			logger.info("error ei ollut null");
			model.addAttribute("error",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (logout != null) {
			logger.info("logout ei ollut null");
			model.addAttribute("logout", "Olet kirjautunut ulos.");
		}
		model.addAttribute("user", new User("", ""));
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String saveUser(Model model, @Valid User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			user.setEmptyPassword("");
			return "registration";
		}
		// tarkistetaan ettei kyseiselle käyttäjänimelle ole jo luotu tiliä
		boolean duplicateUsername = getDao().searchUser(user.getUsername());
		if (!duplicateUsername) {
			user.setRole("ROLE_USER");
			getDao().saveUser(user);
			// jos rekisteröinti onnistuu, palataan loginiin ja printataan
			// esimerkin vuoksi rekisteröitymistiedot,
			// joista käy ilmi että salasana on nyt suojattu encoderilla
			model.addAttribute("success", " Rekisteröinti tehty tiedoilla: "
					+ user.toString());
			return "login";
		} else {
			// jos nimi on jo käytössä, tyhjätään kentät ja palataan
			// rekisteröitymislomakkeeseen
			model.addAttribute("error",
					"Antamallasi sähköpostiosoitteella on jo rekisteröidytty palveluun.");
			user.setUsername("");
			user.setEmptyPassword("");
			model.addAttribute("user", user);
			return "registration";
		}
	}
	
	/*
	 * Metodi, joka palauttaa virheilmoitusten tekstin kustomoituna suomeksi käyttäjän 
	 * näkymään sekä tulostaa poikkeuksen sisällön html-kommenttina.  
	 */
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Virheellinen käyttäjätunnus ja/tai salasana" + "<!--VIRHEILMOITUS: " + exception.toString() + "-->";
		} else if (exception instanceof LockedException) {
			error = "Käyttäjätunnus lukittu liian monen kirjautumisyrityksen vuoksi" + "<!--VIRHEILMOITUS: " + exception.toString() + "-->";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			error = "Yhteysvirhe - yritä hetken kuluttua uudelleen" + "<!--VIRHEILMOITUS: " + exception.toString() + "-->";
		} else {
			error = "Yhteysvirhe - yritä hetken kuluttua uudelleen" + "<!--VIRHEILMOITUS: " + exception.toString() + "-->";
		}

		return error;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		return "403";
	}
}