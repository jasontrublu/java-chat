package dirv.chat.client;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import dirv.chat.Message;

public class MessageSenderStub extends MessageSender {

    private boolean retrieveMessagesWasCalled = false;
    private long lastTimestamp = -1;
    private List<Message> messagesToReturn;

    public MessageSenderStub() {
        this(Collections.emptyList());
    }
    
    public MessageSenderStub(List<Message> messagesToReturn) {
        super(null, "", 0, "");
        this.messagesToReturn = messagesToReturn;
    }
    
    @Override
    public List<Message> retrieveMessagesSince(long timestamp) throws IOException {
        this.retrieveMessagesWasCalled = true;
        this.lastTimestamp = timestamp;
        return messagesToReturn;
    }
    
    public long getLastTimestamp() {
        return lastTimestamp;
    }
    
    public boolean getRetrieveMessagesWasCalled() {
        return retrieveMessagesWasCalled;
    }
}