package poe.spring.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poe.spring.Application;
import poe.spring.domain.User;
import poe.spring.exception.DuplicateLoginBusinessException;
import poe.spring.repository.UserRepository;
import poe.spring.service.UserManagerService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	private static Logger log = LoggerFactory.getLogger(UserRestController.class);


	@Autowired
	private UserManagerService userManagerService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public User save(@RequestBody User user, HttpServletResponse response) {
		User savedUser = null;
		try {
			savedUser = userManagerService.signup(user.getLogin(), user.getPassword());
		} catch (DuplicateLoginBusinessException e) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		System.out.println("user id : " + savedUser);
		return savedUser;
	}

	@GetMapping("/{userId}")
	public User find(@PathVariable("userId") Long userId, HttpServletResponse response) {
		User userFromBdd = userRepository.findOne(userId);
		if (userFromBdd == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

		return userFromBdd;
	}
}
