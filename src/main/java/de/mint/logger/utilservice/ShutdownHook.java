package de.mint.logger.utilservice;

import de.mint.logger.mainservice.SameLogger;
import de.mint.logger.objectservice.SameLoggerObject;

import java.io.IOException;

public class ShutdownHook {

    public void initializedShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::initializedShutdown));
    }

    private void initializedShutdown() {
        try {
            SameLoggerObject.getSameLoggerObject().getProcessManager().killProcess();
            SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().getFileWriter().close();
            SameLoggerObject.getSameLoggerObject().getLogger().info(SameLoggerObject.getSameLoggerObject().getOutputMessages().getFileSavedSuccessful().replace("%fileName%" , SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().getFileName()));
            SameLoggerObject.getSameLoggerObject().getLogger().info(SameLoggerObject.getSameLoggerObject().getOutputMessages().getPoweredDown());
        } catch (final IOException exception) {
            SameLoggerObject.getSameLoggerObject().getLogger().warning(exception.getMessage());
        }
    }

}
