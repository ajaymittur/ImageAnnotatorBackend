package dev.cse.imageannotatorbackend.model;

import javax.persistence.*;

@Entity
@Table(name = "Annotator_Messages")
@IdClass(AnnotatorMessagesId.class)
public class AnnotatorMessages {
    @Id
    @Column(name = "AnnotatorId")
    private long annotatorId;

    @Id
    @Column(name = "MessageId")
    private long messageId;

    public AnnotatorMessages() {}

    public AnnotatorMessages(long annotatorId, long messageId) {
        this.annotatorId = annotatorId;
        this.messageId = messageId;
    }

    public long getAnnotatorId() {
        return annotatorId;
    }

    public void setAnnotatorId(long annotatorId) {
        this.annotatorId = annotatorId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
