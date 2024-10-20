package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for CommandTextHistory.
 */
public class CommandTextHistoryTest {

    @Test
    public void getPreviousCommand() {
        CommandTextHistory commandTextHistory = new CommandTextHistory();
        commandTextHistory.addCommand("list");
        commandTextHistory.addCommand("invalid command"); // works for invalid commands too
        assertEquals("invalid command", commandTextHistory.getPreviousCommand());
        assertEquals("list", commandTextHistory.getPreviousCommand());
        assertEquals("list", commandTextHistory.getPreviousCommand());
        // should not go beyond the first command
    }

    @Test
    public void getNextCommand() {
        CommandTextHistory commandTextHistory = new CommandTextHistory();
        commandTextHistory.addCommand("list");
        commandTextHistory.addCommand("invalid command"); // works for invalid commands too
        assertEquals("", commandTextHistory.getNextCommand());

        commandTextHistory.getPreviousCommand();
        assertEquals("invalid command", commandTextHistory.getNextCommand());
        assertEquals("invalid command", commandTextHistory.getNextCommand());
        // should not go beyond the last command, even if getNextCommand is called multiple times
    }

}
