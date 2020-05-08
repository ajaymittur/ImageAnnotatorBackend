package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.Messages;
import dev.cse.imageannotatorbackend.model.MessagesId;
import dev.cse.imageannotatorbackend.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {
    private MessagesRepository messagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void addMessage(Messages message) {
        messagesRepository.save(message);
    }

    public void deleteMessage(MessagesId messageId) {
        messagesRepository.deleteById(messageId);
    }

    public List<String> getMessages(String username) {
        List<Messages> messages =  messagesRepository.findByUsername(username);
        List<String> text  = new ArrayList<>();

        for (Messages msg: messages) {
            text.add(msg.getMessage());
        }

        return text;
    }
}
