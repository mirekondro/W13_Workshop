package dk.easv.w13_workshop.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * REPOSITORY PATTERN – Mock repository (for testing / offline demo).
 *
 * Records every command call so tests can assert what was sent.
 * Use this when the API server is not available.
 *
 * Inject it the same way as ApiFoxRepository:
 *   new CommandManager(new MockFoxRepository())
 */
public class MockFoxRepository implements FoxRepository {

    public record SentCommand(int group, String command, String value) {}

    private final List<SentCommand> sentCommands = new ArrayList<>();

    @Override
    public void sendCommand(int groupNumber, String command, String value) {
        SentCommand sent = new SentCommand(groupNumber, command, value);
        sentCommands.add(sent);
        System.out.printf("[MOCK] Group %d  →  %s %s%n", groupNumber, command, value);
    }

    /** Returns an unmodifiable view of every command that was sent. */
    public List<SentCommand> getSentCommands() {
        return Collections.unmodifiableList(sentCommands);
    }

    public void reset() {
        sentCommands.clear();
    }
}
