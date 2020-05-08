package dev.cse.imageannotatorbackend.model;

import java.io.Serializable;
import java.util.Objects;

public class MessagesId implements Serializable {
    private String username;
    private String message;

    public MessagesId() {

    }

    public MessagesId(String username, String message) {
        this.username = username;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesId that = (MessagesId) o;
        return username.equals(that.username) &&
                message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, message);
    }
}
