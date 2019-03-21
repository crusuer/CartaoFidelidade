package br.com.fidelity.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fidelity.entity.AppUser;
import br.com.fidelity.repository.AppUserRepository;

@Controller
public class MainController {
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String index() {    	
        return "index";
    }
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastroPage(Model model) {
		model.addAttribute("user", new AppUser());
		return "cadastroPage";
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String cadastroUser(@Valid AppUser user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "cadastroPage";
		} else {
			user.setEncrytedPassword(passwordEncoder.encode(user.getEncrytedPassword()));
			appUserRepository.save(user);

			return "index";
		}
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Model model, Authentication authentication) {
		if(authentication != null) {
			model.addAttribute("user", authentication.getName());
		}
		return "user/homePage";
	}
	@RequestMapping(value = "/vencimento", method = RequestMethod.GET)
	public String vencimentoPage(Model model) {
		return "user/vencimentoPage";
	}
}
