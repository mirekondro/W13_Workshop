package dk.easv.w13_workshop.command;


import com.foxconfig.iterator.CommandIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * COMMAND PATTERN – Invoker.
 *
 * The CommandManager collects FoxCommand objects as the user changes
 * parameters in the UI.  When the user clicks "Send to Fox", executeAll()
 * is called, which creates a CommandIterator and runs every queued command.
 */
public class CommandManager {

    private final List<FoxCommand> pendingCommands = new ArrayList<>();

    /** Add a command to the queue (called when a field value changes). */
    public void addCommand(FoxCommand command) {
        pendingCommands.add(command);
    }

    /** How many commands are waiting to be sent. */
    public int pendingCount() {
        return pendingCommands.size();
    }

    /**
     * ITERATOR PATTERN – creates an iterator and steps through every command.
     *
     * @param listener receives a callback after each command executes
     *                 (used to update the UI log in real-time).
     */
    public void executeAll(ExecutionListener listener) {
        CommandIterator iterator = new CommandIterator(pendingCommands);

        while (iterator.hasNext()) {
            FoxCommand cmd = iterator.next();
            try {
                cmd.execute();
                if (listener != null) {
                    listener.onCommandExecuted(cmd, true, null);
                }
            } catch (Exception e) {
                if (listener != null) {
                    listener.onCommandExecuted(cmd, false, e.getMessage());
                }
            }
        }
        pendingCommands.clear();
    }

    /** Clear all pending commands without executing them. */
    public void clearCommands() {
        pendingCommands.clear();
    }

    /** Read-only view of pending commands (for the UI preview). */
    public List<FoxCommand> getPendingCommands() {
        return List.copyOf(pendingCommands);
    }

    // ── Listener interface ────────────────────────────────────────────────────

    @FunctionalInterface
    public interface ExecutionListener {
        void onCommandExecuted(FoxCommand command, boolean success, String errorMessage);
    }
}
