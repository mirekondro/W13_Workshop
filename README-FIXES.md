# Fox Configurator - Orienteering Equipment Setup

## What was fixed:

1. **Renamed and restructured the application:**
   - Changed from generic "HelloApplication" to "FoxConfigApplication"
   - Created "FoxConfigController" instead of the broken "HelloController"
   - Created new FXML file "fox-config-view.fxml" with correct controller reference

2. **Created missing business logic classes:**
   - `FoxConfig` - Model class for fox configuration parameters
   - `FoxCommand` interface - Command pattern implementation
   - `ConfigureParameterCommand` - Concrete command implementation
   - `CommandManager` - Command manager with iterator pattern
   - `FoxRepository` interface - Repository pattern
   - `ApiFoxRepository` and `MockFoxRepository` - Repository implementations

3. **Fixed package structure:**
   - Moved all fox-related classes to `dk.easv.w13_workshop.foxconfig.*` packages
   - Updated imports and module-info.java accordingly
   - Removed old conflicting files

4. **Fixed application launcher:**
   - Updated `Launcher.java` to use `FoxConfigApplication`
   - Updated `pom.xml` to point to the correct main class
   - Fixed window size from 320x240 to 800x900 (appropriate for the complex UI)

## How to run:

```bash
cd /Users/miroslavondrousek/GitHub/W13_Workshop
mvn clean javafx:run
```

Or alternatively:
```bash
mvn clean compile
mvn javafx:run
```

## Features:

The Fox Configurator application allows Henrik to:

1. **Configure fox parameters** without using terminal commands:
   - Test Begin Time
   - Hunt Start Time  
   - Hunt Stop Time
   - Fox ID (A, U, V, H, 5)
   - Frequency (Hz)
   - Repeat Period (minutes)
   - Fox Call (≤15 characters)

2. **Queue commands** using the Command Pattern:
   - Each parameter has a "Queue" button to add it to the pending queue
   - Commands are displayed in a text area before execution

3. **Choose repository** for sending commands:
   - API mode: Connects to http://10.5.10.10:8080 (for real fox machines)
   - Mock mode: Offline simulation for testing

4. **Execute all queued commands** with one button:
   - Uses Iterator Pattern to process all commands
   - Shows execution results and status
   - Background execution keeps UI responsive

The application successfully implements the three design patterns requested:
- **Command Pattern**: Each configuration is a command object
- **Iterator Pattern**: CommandManager iterates through queued commands
- **Repository Pattern**: Abstracts the communication method (API vs Mock)
