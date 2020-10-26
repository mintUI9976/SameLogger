package de.mint.logger.utilservice;

import java.util.Calendar;

public class Logger {

    private final Calendar calendar = Calendar.getInstance();

    public void info(final String message) {
        System.out.println("|" + Level.INFO.name() + "|" + this.calendar.getTime() + " | " + message);
    }

    public void warning(final String message) {
        System.out.println("|" + Level.WARNING.name() + "|" + this.calendar.getTime() + " | " + message);
    }

    public String stringRework(final String message) {
        return "|" + Level.LOG.name() + "|" + this.calendar.getTime() + " | " + message;
    }

    public enum Level {
        INFO,
        WARNING,
        LOG,
    }

}
