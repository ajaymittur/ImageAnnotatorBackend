package dev.cse.imageannotatorbackend.model;

import javax.persistence.*;

@Entity
@IdClass(MessagesId.class)
public class Messages {
    @Id
    @Column(name = "Username")
    private String username;

    @Id
    @Column(name = "Message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Annotators annotators;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Annotators getAnnotators() {
        return annotators;
    }

    public void setAnnotators(Annotators annotators) {
        this.annotators = annotators;
    }
}
