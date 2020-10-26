package de.mint.logger.utilservice;

import de.mint.logger.mainservice.SameLogger;
import de.mint.logger.objectservice.SameLoggerObject;

import java.io.IOException;

public class ShutdownHook {

    public void initializedShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::initializedShutdown));
    }

    public void initializedShutdown() {
        try {
            SameLoggerObject.getSameLoggerObject().getProcessManager().killProcess();
            SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().getFileWriter().close();
            SameLoggerObject.getSameLoggerObject().getLogger().info("The file '" + SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().getFileName() + "' was saved successfully");
            SameLoggerObject.getSameLoggerObject().getLogger().info("The logger is now powered down");
        } catch (final IOException exception) {
            SameLoggerObject.getSameLoggerObject().getLogger().warning(exception.getMessage());
        }
    }

}
