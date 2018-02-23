package poe.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poe.spring.annotation.Chrono;
import poe.spring.domain.User;
import poe.spring.exception.DuplicateLoginBusinessException;
import poe.spring.repository.UserRepository;

@Service
@Chrono
public class UserManagerService {

	@Autowired
	private UserRepository userRepository;

	public User signup(String login, String password) throws DuplicateLoginBusinessException {
		User user = null;
		// on vérifie que le login n'est pas déjà utilisé
		if (userRepository.findByLogin(login) == null) {
			user = new User();
			user.setLogin(login);
			user.setPassword(password);
			userRepository.save(user);
		} else {
			throw new DuplicateLoginBusinessException();
		}
		return user;
	}


	public void foo() {
		System.out.println("bar");
	}
}
