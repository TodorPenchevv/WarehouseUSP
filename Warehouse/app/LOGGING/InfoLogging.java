
package app.logging;

public class InfoLogging extends Logging {
    public void log(String msg) {
        getLogger().info(msg);
    }
}
