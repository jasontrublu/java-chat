package dirv.chat;

public class Message {

    private final long timestamp;
    private final String user;
    private final String message;

    public Message(long timestamp, String user, String message) {
        this.timestamp = timestamp;
        this.user = user;
        this.message = message;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public String getUser() {
        return user;
    }
    
    public String getMessage() {
        return message;
    }

    public String asResponseString() {
        return timestamp + System.lineSeparator() +
            user + System.lineSeparator() +
            message + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (timestamp != message1.timestamp) return false;
        if (user != null ? !user.equals(message1.user) : message1.user != null) return false;
        return message != null ? message.equals(message1.message) : message1.message == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
