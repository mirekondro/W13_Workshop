package dk.easv.w13_workshop.repository;


/**
 * REPOSITORY PATTERN – Alternative concrete repository (Telnet terminal).
 *
 * This is a STUB that shows how easy it is to switch transport.
 * Replace the body of sendCommand() with real Telnet I/O (e.g. using
 * Apache Commons Net's TelnetClient) to connect to a physical Fox machine.
 *
 * To activate: pass   new TelnetFoxRepository("192.168.1.100", 23)
 * to the CommandManager instead of ApiFoxRepository – nothing else changes.
 */
public class TelnetFoxRepository implements FoxRepository {

    private final String host;
    private final int    port;

    public TelnetFoxRepository(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void sendCommand(int groupNumber, String command, String value) {
        // TODO: open Telnet session, authenticate, send  "command value"  and close.
        // Example with Apache Commons Net:
        //
        //   TelnetClient telnet = new TelnetClient();
        //   telnet.connect(host, port);
        //   OutputStream out = telnet.getOutputStream();
        //   String line = command + " " + value + "\r\n";
        //   out.write(line.getBytes(StandardCharsets.US_ASCII));
        //   out.flush();
        //   telnet.disconnect();

        System.out.printf("[TELNET STUB] %s:%d  →  %s %s%n",
                host, port, command, value);

        throw new UnsupportedOperationException(
                "TelnetFoxRepository is a stub – implement Telnet I/O here.");
    }
}
