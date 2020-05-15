package dev.cse.imageannotatorbackend.model;

import javax.persistence.*;

@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MessageId")
    private long id;

    @Column(name = "Message")
    private String message;

    public Messages() {}

    public Messages(String message) {
        this.message = message;
    }

    public Messages(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
