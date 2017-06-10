package dirv.chat.server.commands;

import dirv.chat.Message;
import dirv.chat.server.MessageRepositorySpy;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RegisterUserCommandTest extends CommandTest {

    private final List<String> users = new ArrayList<>();
    private MessageRepositorySpy messageRepository = new MessageRepositorySpy();

    @Test
    public void addsNewName() throws IOException {
        executeCommand("Donald\n");
        assertThat(users, hasItem("Donald"));
    }

    @Test
    public void acknowledgesAdd() throws IOException {
        String output = executeCommand("Donald\n");
        assertEquals("OK\n", output);
    }

    @Test
    public void doesNotAddIfNoNameGiven() throws IOException {
        String output = executeCommand("");
        assertEquals(0, users.size());
        assertEquals("ERROR\n", output);
    }

    @Test
    public void doesNotAddIfNameIsBlank() throws IOException {
        String output = executeCommand("\n");
        assertEquals(0, users.size());
        assertEquals("ERROR\n", output);
    }

    @Test
    public void doesNotAddAlreadyRegisteredNames() throws IOException {
        executeCommand("Donald\n");
        String output = executeCommand("Donald\n");
        assertEquals(1, users.size());
        assertEquals("ERROR\n", output);
    }

    @Test
    public void trimsNames() throws IOException {
        executeCommand(" Donald \n");
        assertThat(users, hasItem("Donald"));
    }

    @Test
    public void sendMessageIfNewUserRegisters() throws Exception {
        executeCommand("Donald\n");

        Message message = messageRepository.getMessages().get(0);
        assertEquals("Donald", message.getUser());
        assertEquals("User Donald has registered", message.getMessage());
    }

    protected Command command() {
        return new RegisterUserCommand(users, messageRepository);
    }
}
