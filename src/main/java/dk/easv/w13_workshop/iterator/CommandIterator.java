package dk.easv.w13_workshop.iterator;

import com.foxconfig.command.FoxCommand;
import java.util.Iterator;
import java.util.List;

/**
 * ITERATOR PATTERN – Concrete iterator.
 *
 * Wraps a list of FoxCommand objects and exposes a standard
 * hasNext() / next() interface.  The CommandManager uses this to loop
 * through the queue without exposing the underlying List directly.
 *
 * Using a custom iterator (rather than a for-each loop) makes the
 * pattern explicit and allows future extensions such as:
 *   - filtering skipped commands
 *   - pausing between commands
 *   - reporting progress by index
 */
public class CommandIterator implements Iterator<FoxCommand> {

    private final List<FoxCommand> commands;
    private int currentIndex = 0;

    public CommandIterator(List<FoxCommand> commands) {
        this.commands = commands;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < commands.size();
    }

    @Override
    public FoxCommand next() {
        return commands.get(currentIndex++);
    }

    /** Zero-based position of the next command to be returned. */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /** Total number of commands in the collection. */
    public int size() {
        return commands.size();
    }
}

