package dirv.chat.server.commands;

import dirv.chat.server.MessageRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RegisterUserCommand extends RecognizedCommand {

    private final List<String> users;
    private MessageRepository messageRepository;

    public RegisterUserCommand(List<String> users, MessageRepository messageRepository) {
        super("1");
        this.users = users;
        this.messageRepository = messageRepository;
    }

    @Override
    public void execute(BufferedReader reader, PrintWriter printWriter) throws IOException {
        String user = reader.readLine();

        boolean added = attemptAdd(user);
        messageRepository.receiveMessage(user, String.format("User %s has registered", user));
        printWriter.println(added ? "OK" : "ERROR");
    }

    private boolean attemptAdd(String user) {
        if (user == null) return false;
        user = user.trim();
        if (user.isEmpty()) return false;
        if (users.contains(user)) return false;
        users.add(user);
        return true;
    }

}
