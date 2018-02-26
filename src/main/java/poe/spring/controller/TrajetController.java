package poe.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poe.spring.domain.Trajet;
import poe.spring.repository.TrajetRepository;
import poe.spring.service.TrajetManagerService;

import java.util.List;

@Controller
@RequestMapping("/trajet")
public class TrajetController {

	@Autowired
	TrajetManagerService trajetManagerService;

	@GetMapping
	public String list(@RequestParam(required = false) Long userId, @RequestParam(required = false) String town,  Model model) {

		List<Trajet> trajets = trajetManagerService.search(userId, town);

		model.addAttribute("trajets", trajets);

		return "/trajet/list";
	}

}