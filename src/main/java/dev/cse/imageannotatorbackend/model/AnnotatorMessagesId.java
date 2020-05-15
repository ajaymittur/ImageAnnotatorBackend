package dev.cse.imageannotatorbackend.model;

import java.io.Serializable;
import java.util.Objects;

public class AnnotatorMessagesId implements Serializable {
    private long annotatorId;
    private long messageId;

    public AnnotatorMessagesId() {

    }

    public AnnotatorMessagesId(long annotatorId, long messageId) {
        this.annotatorId = annotatorId;
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnotatorMessagesId that = (AnnotatorMessagesId) o;
        return annotatorId == that.annotatorId &&
                messageId == that.messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(annotatorId, messageId);
    }


}
