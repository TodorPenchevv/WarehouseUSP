package app.logging;

import app.gui.AlertBox;

public class ErrorLogging extends Logging {
    public void log(String msg) {
        getLogger().error(msg);
        AlertBox.display("Грешка", "Възникна неочаквана грешка! Операцията неуспешна!");
    }
}
