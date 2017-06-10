package dirv.chat.server;

import dirv.chat.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MessageRepositorySpy implements MessageRepository {

    private final List<Message> allMessages = new ArrayList<>();
    private long askedForMessagesSince;

    @Override
    public void receiveMessage(String user, String message) {
        allMessages.add(new Message(-1, user, message));
    }

    public List<Message> getMessages() {
        return allMessages;
    }

    public long getAskedForMessagesSince() {
        return askedForMessagesSince;
    }

    public void add(long timestamp, String name, String message) {
        allMessages.add(new Message(timestamp, name, message));
    }

    @Override
    public Stream<Message> getMessagesSince(long timestamp) {
        this.askedForMessagesSince = timestamp;
        return allMessages.stream();
    }
}
