package de.mint.logger.mainservice;

import de.mint.logger.objectservice.SameLoggerObject;

import java.util.Objects;

public class SameLogger {

    public static void main(final String... strings) {
        if (Objects.requireNonNull(strings).length != 0){
            SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().initializedLogo();
            SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().bootStrap(strings);
            SameLoggerObject.getSameLoggerObject().getShutdownHook().initializedShutdownHook();
        } else {
            System.exit(-1);
        }
    }


}
