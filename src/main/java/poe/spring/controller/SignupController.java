package poe.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poe.spring.exception.DuplicateLoginBusinessException;
import poe.spring.form.LoginForm;
import poe.spring.service.UserManagerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignupController {

	private static Logger log = LoggerFactory.getLogger(SignupController.class);


	@Autowired
	UserManagerService userManagerService;

	@GetMapping
	public String index(LoginForm form) {
		return "signup"; // affiche signup.html
	}

	@PostMapping
	public String save(@Valid LoginForm form,
					   BindingResult bindingResult,
					   RedirectAttributes attr,
					   Model model) {

		log.info("ajout d'un utilisateur login: {0}", form.getLogin());

		if (bindingResult.hasErrors()) {
			return "signup";
		}


		try {
			userManagerService.signup(form.getLogin(), form.getPassword());
		} catch (DuplicateLoginBusinessException e) {
			model.addAttribute("error", "Ce login est déjà utilisé!");
			return "signup";
		}
		attr.addAttribute("userName", form.getLogin());
		return "redirect:/signup/success";
	}

	@GetMapping("/success")
	public String success(@RequestParam("userName") String userName, Model model) {
		model.addAttribute("name", userName);
		return "success"; // success.html
	}
}