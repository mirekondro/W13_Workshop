package dk.easv.w13_workshop.foxconfig.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Command manager that implements the Command pattern with iterator support.
 */
public class CommandManager {
    private final List<FoxCommand> commands = new ArrayList<>();

    public void addCommand(FoxCommand command) {
        commands.add(command);
    }

    public void clearCommands() {
        commands.clear();
    }

    public int pendingCount() {
        return commands.size();
    }

    /**
     * Execute all commands using iterator pattern.
     * @param callback callback for each command execution
     */
    public void executeAll(CommandExecutionCallback callback) {
        List<FoxCommand> toExecute = new ArrayList<>(commands);
        commands.clear();

        for (FoxCommand cmd : toExecute) {
            try {
                boolean success = cmd.execute();
                callback.onCommandExecuted(cmd, success, null);
            } catch (Exception e) {
                callback.onCommandExecuted(cmd, false, e.getMessage());
            }
        }
    }

    @FunctionalInterface
    public interface CommandExecutionCallback {
        void onCommandExecuted(FoxCommand command, boolean success, String error);
    }
}
