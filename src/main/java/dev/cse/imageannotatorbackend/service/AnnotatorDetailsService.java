package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.Annotators;
import dev.cse.imageannotatorbackend.model.CustomAnnotatorDetails;
import dev.cse.imageannotatorbackend.repository.AnnotatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnotatorDetailsService implements UserDetailsService {

	private AnnotatorsRepository annotatorsRepo;

	@Autowired
	public AnnotatorDetailsService(AnnotatorsRepository annotatorsRepo) {
		this.annotatorsRepo = annotatorsRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Annotators> annotator = annotatorsRepo.findByUsername(username);
		return new CustomAnnotatorDetails(annotator.orElseThrow(() -> new UsernameNotFoundException("Username not found")));
	}
}
