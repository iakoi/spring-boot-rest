package poe.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import poe.spring.annotation.Chrono;
import poe.spring.domain.User;
import poe.spring.exception.DuplicateLoginBusinessException;
import poe.spring.repository.UserRepository;

import java.util.ArrayList;

@Service
@Chrono
public class UserManagerService {

	private static Logger log = LoggerFactory.getLogger(UserManagerService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;

	public User signup(String login, String password) throws DuplicateLoginBusinessException {
		User user = null;
		// on vérifie que le login n'est pas déjà utilisé
		if (userRepository.findByLogin(login) == null) {
			user = new User();
			user.setLogin(login);
			user.setPassword(password);
			userRepository.save(user);

			inMemoryUserDetailsManager.createUser(new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<GrantedAuthority>()));

		} else {
			throw new DuplicateLoginBusinessException();
		}
		return user;
	}
}
