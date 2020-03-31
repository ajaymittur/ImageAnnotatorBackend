package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.Users;
import dev.cse.imageannotatorbackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
	private UsersRepository usersRepository;

	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public void addUser(Users user) {
		usersRepository.save(user);
	}

	public Optional<Users> getUser(String username) {
		return usersRepository.findByUsername(username);
	}
}
