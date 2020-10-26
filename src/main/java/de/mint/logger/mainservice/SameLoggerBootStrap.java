package de.mint.logger.mainservice;

import de.mint.logger.objectservice.SameLoggerObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.stream.Collectors;

public class SameLoggerBootStrap {

    private FileWriter fileWriter;
    private final String version = "v0.0.1";
    private final String developer = "mint (Niklas)";
    private final String github = "https://github.com/mInT-runs";
    private final UUID uuid = UUID.randomUUID();
    private final String fileName = "SameLogger/SameLogger_" + this.uuid + ".log";

    private boolean initializedLoggerFolder() {
        final String folderName = "SameLogger/";
        if (!SameLoggerObject.getSameLoggerObject().getFileInterpreter().isLoggerFolderExist(folderName)) {
            if (SameLoggerObject.getSameLoggerObject().getFileInterpreter().createLoggerFolder(folderName)) {
                SameLoggerObject.getSameLoggerObject().getLogger().info("The folder '" + folderName + "' was successfully created");
                return true;
            } else {
                SameLoggerObject.getSameLoggerObject().getLogger().warning("Error the folder '" + folderName + "' could not be created");
                return false;
            }
        } else {
            return true;
        }
    }

    private boolean initializedLoggerFile() {
        if (!SameLoggerObject.getSameLoggerObject().getFileInterpreter().isLoggerFileExist(this.fileName)) {
            if (SameLoggerObject.getSameLoggerObject().getFileInterpreter().createLoggerFile(this.fileName)) {
                SameLoggerObject.getSameLoggerObject().getLogger().info("The file '" + this.fileName + "' was successfully created");
                return true;
            } else {
                SameLoggerObject.getSameLoggerObject().getLogger().warning("Error the file '" + this.fileName + "' could not be created");
                return false;
            }
        } else {
            return true;
        }
    }

    private void initializedLoggerRunnable() throws IOException {
        this.fileWriter = new FileWriter(this.fileName);
        final Timer timer = new Timer();
        final InputStreamReader inputStreamReader = new InputStreamReader(SameLoggerObject.getSameLoggerObject().getProcessManager().inputStreamOfProcess());
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        timer.scheduleAtFixedRate(new TimerTask() {
            String line;

            @Override
            public void run() {
                try {
                    this.line = bufferedReader.readLine();
                    if (this.line != null) {
                        SameLoggerObject.getSameLoggerObject().getLogger().info(this.line);
                        SameLoggerBootStrap.this.fileWriter.write(SameLoggerObject.getSameLoggerObject().getLogger().stringRework(this.line) + "\n");
                        SameLoggerBootStrap.this.fileWriter.flush();
                    } else {
                        bufferedReader.close();
                        timer.cancel();
                    }
                } catch (final IOException exception) {
                    exception.printStackTrace();
                }
            }
        }, 0, 1);
    }

    void bootStrap(final String... command) {

        if (this.initializedLoggerFolder() && this.initializedLoggerFile()) {
            SameLoggerObject.getSameLoggerObject().getLogger().info("The folder and file's initialization was successful");
            if (command.length == 1) {
                SameLoggerObject.getSameLoggerObject().getProcessManager().executeProcess(command[0]);
            } else {
                final String stringBuilder = Arrays.stream(command).map(strings -> strings + " ").collect(Collectors.joining());
                SameLoggerObject.getSameLoggerObject().getProcessManager().executeProcess(stringBuilder);
            }
            try {
                this.initializedLoggerRunnable();
            } catch (final IOException exception) {
                exception.printStackTrace();
            }
        } else {
            System.exit(-1);
        }
    }

    public String getFileName() {
        return this.fileName;
    }

    public FileWriter getFileWriter() {
        return this.fileWriter;
    }

    void initializedLogo() {
        SameLoggerObject.getSameLoggerObject().getLogger().info("\n  ____                       _                                \n" +
                " / ___|  __ _ _ __ ___   ___| |    ___   __ _  __ _  ___ _ __ \n" +
                " \\___ \\ / _` | '_ ` _ \\ / _ \\ |   / _ \\ / _` |/ _` |/ _ \\ '__|\n" +
                "  ___) | (_| | | | | | |  __/ |__| (_) | (_| | (_| |  __/ |   \n" +
                " |____/ \\__,_|_| |_| |_|\\___|_____\\___/ \\__, |\\__, |\\___|_|   \n" +
                "                                        |___/ |___/           \n");
        System.out.println("\nversion: " + this.version + " - developer: " + this.developer + " - github: " + this.github + "\n");
    }

    public String getVersion() {
        return version;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getGithub() {
        return github;
    }

    public UUID getUuid() {
        return uuid;
    }
}
