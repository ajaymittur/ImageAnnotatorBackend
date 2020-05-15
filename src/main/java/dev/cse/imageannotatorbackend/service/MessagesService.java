package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.AnnotatorMessages;
import dev.cse.imageannotatorbackend.model.AnnotatorMessagesId;
import dev.cse.imageannotatorbackend.model.Annotators;
import dev.cse.imageannotatorbackend.model.Messages;
import dev.cse.imageannotatorbackend.repository.AnnotatorMessagesRepository;
import dev.cse.imageannotatorbackend.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MessagesService {
    private MessagesRepository messagesRepository;
    private AnnotatorsService annotatorsService;
    private AnnotatorMessagesRepository annotatorMessagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository, AnnotatorsService annotatorsService, AnnotatorMessagesRepository annotatorMessagesRepository) {
        this.messagesRepository = messagesRepository;
        this.annotatorsService = annotatorsService;
        this.annotatorMessagesRepository = annotatorMessagesRepository;
    }

    public void addMessage(String username, String message) {
        Messages msg = new Messages(message);
        messagesRepository.save(msg);
        Optional<Annotators> annotator = annotatorsService.getAnnotator(username);
        annotator.ifPresent(annotators -> annotatorMessagesRepository.save(new AnnotatorMessages(annotators.getId(), msg.getId())));
    }

    public void deleteMessage(String username, long messageId) {
        Optional<Annotators> annotator = annotatorsService.getAnnotator(username);
        annotator.ifPresent(annotators -> annotatorMessagesRepository.deleteById(new AnnotatorMessagesId(annotators.getId(), messageId)));
        messagesRepository.deleteById(messageId);
    }

    public List<Messages> getMessages(String username) {
        long annotatorId = 0;
        Optional<Annotators> annot = annotatorsService.getAnnotator(username);

        if (annot.isPresent())
            annotatorId = annot.get().getId();
        else
            throw new UsernameNotFoundException("Invalid annotator username, couldn't find annotator");

        List<Long> msgIds = annotatorMessagesRepository.findByAnnotatorId(annotatorId);

        return messagesRepository.findByIds(msgIds);
    }
}
