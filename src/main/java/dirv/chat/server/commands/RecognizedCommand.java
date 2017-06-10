package dirv.chat.server.commands;

public abstract class RecognizedCommand implements Command {

    private final String commandType;

    RecognizedCommand(String commandType) {
        this.commandType = commandType;
    }

    public boolean respondsTo(String commandType) {
        return this.commandType.equals(commandType);
    }

}
