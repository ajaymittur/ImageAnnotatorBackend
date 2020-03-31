package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.Annotators;
import dev.cse.imageannotatorbackend.repository.AnnotatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnotatorsService {
	private AnnotatorsRepository annotatorsRepository;

	@Autowired
	public AnnotatorsService(AnnotatorsRepository annotatorsRepository) {
		this.annotatorsRepository = annotatorsRepository;
	}

	public void addAnnotator(Annotators annotator) {
		annotatorsRepository.save(annotator);
	}

	public Optional<Annotators> getAnnotator(String username) {
		return annotatorsRepository.findByUsername(username);
	}
}
