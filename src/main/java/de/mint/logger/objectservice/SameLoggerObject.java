package de.mint.logger.objectservice;

import de.mint.logger.mainservice.SameLoggerBootStrap;
import de.mint.logger.utilservice.FileInterpreter;
import de.mint.logger.utilservice.Logger;
import de.mint.logger.utilservice.ProcessManager;
import de.mint.logger.utilservice.ShutdownHook;

public class SameLoggerObject {

    private static final SameLoggerObject sameLoggerObject = new SameLoggerObject();

    private final Logger logger = new Logger();

    private final ShutdownHook shutdownHook = new ShutdownHook();

    private final FileInterpreter fileInterpreter = new FileInterpreter();

    private final ProcessManager processManager = new ProcessManager();

    private final SameLoggerBootStrap sameLoggerBootStrap = new SameLoggerBootStrap();

    public SameLoggerBootStrap getSameLoggerBootStrap() {
        return this.sameLoggerBootStrap;
    }

    public ShutdownHook getShutdownHook() {
        return this.shutdownHook;
    }

    public ProcessManager getProcessManager() {
        return this.processManager;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public FileInterpreter getFileInterpreter() {
        return this.fileInterpreter;
    }

    public static SameLoggerObject getSameLoggerObject() {
        return SameLoggerObject.sameLoggerObject;
    }
}
