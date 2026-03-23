package dk.easv.w13_workshop.foxconfig.command;

import dk.easv.w13_workshop.foxconfig.repository.FoxRepository;

/**
 * Concrete command for configuring a fox parameter.
 */
public class ConfigureParameterCommand implements FoxCommand {
    private final FoxRepository repository;
    private final int group;
    private final String command;
    private final String value;

    public ConfigureParameterCommand(FoxRepository repository, int group, String command, String value) {
        this.repository = repository;
        this.group = group;
        this.command = command;
        this.value = value;
    }

    @Override
    public boolean execute() {
        return repository.sendCommand(group, command, value);
    }

    @Override
    public String getDescription() {
        return "Group " + group + ": " + command + " = " + value;
    }
}
