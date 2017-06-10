package dirv.chat.server.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface Command {
    boolean respondsTo(String commandType);

    void execute(BufferedReader reader, PrintWriter printWriter) throws IOException;
}
