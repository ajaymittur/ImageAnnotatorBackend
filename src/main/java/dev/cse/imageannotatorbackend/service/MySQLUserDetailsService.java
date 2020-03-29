package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.CustomUserDetails;
import dev.cse.imageannotatorbackend.model.Users;
import dev.cse.imageannotatorbackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MySQLUserDetailsService implements UserDetailsService {

	private UsersRepository usersRepo;

	@Autowired
	public MySQLUserDetailsService(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user = usersRepo.findByUsername(username);
		return new CustomUserDetails(user.orElseThrow(() -> new UsernameNotFoundException("Username not found")));
	}
}
