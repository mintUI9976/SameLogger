package de.mint.logger.utilservice;

import de.mint.logger.objectservice.SameLoggerObject;

import java.io.File;
import java.io.IOException;

public class FileInterpreter {


    public boolean isLoggerFileExist(final String fileName) {
        final File file = new File(fileName);
        return file.exists();
    }

    public boolean isLoggerFolderExist(final String folderName) {
        final File file = new File(folderName);
        return file.exists();
    }

    public boolean createLoggerFile(final String fileName) {
        final File file = new File(fileName);
        try {
            return file.createNewFile();
        } catch (final SecurityException | IOException exception) {
            SameLoggerObject.getSameLoggerObject().getLogger().warning(exception.getMessage());
            return false;
        }
    }

    public boolean clearLoggerFile(final String fileName) {
        final File file = new File(fileName);
        try {
            return file.delete();
        } catch (final SecurityException exception) {
            SameLoggerObject.getSameLoggerObject().getLogger().warning(exception.getMessage());
            return false;
        }
    }

    public boolean createLoggerFolder(final String folderName) {
        final File directory = new File(folderName);
        try {
            return directory.mkdir();
        } catch (final SecurityException exception) {
            SameLoggerObject.getSameLoggerObject().getLogger().warning(exception.getMessage());
            return false;
        }
    }

}
