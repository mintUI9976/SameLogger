package de.mint.logger.mainservice;

import de.mint.logger.objectservice.SameLoggerObject;

public class SameLogger {

    public static void main(final String... strings) {
        SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().initializedLogo();
        SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().bootStrap(strings);
        SameLoggerObject.getSameLoggerObject().getShutdownHook().initializedShutdownHook();
    }


}
