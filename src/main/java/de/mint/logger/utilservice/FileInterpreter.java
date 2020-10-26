package de.mint.logger.utilservice;

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
            exception.printStackTrace();
            return false;
        }
    }

    public boolean clearLoggerFile(final String fileName) {
        final File file = new File(fileName);
        try {
            return file.delete();
        } catch (final SecurityException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean createLoggerFolder(final String folderName) {
        final File directory = new File(folderName);
        try {
            return directory.mkdir();
        } catch (final SecurityException exception) {
            exception.printStackTrace();
            return false;
        }
    }

}
