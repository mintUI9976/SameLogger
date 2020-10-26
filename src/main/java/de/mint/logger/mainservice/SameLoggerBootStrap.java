package de.mint.logger.mainservice;

import de.mint.logger.objectservice.SameLoggerObject;
import de.mint.logger.utilservice.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.stream.Collectors;

public class SameLoggerBootStrap {

    private FileWriter fileWriter;
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

    private void initializedLoggerRunnable(final InputStream inputStream) throws IOException {
        this.fileWriter = new FileWriter(this.fileName);
        final Timer timer = new Timer();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
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
                    SameLoggerObject.getSameLoggerObject().getLogger().warning(exception.getMessage());
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
                this.initializedLoggerRunnable(this.checkIsJarFile(command) ? SameLoggerObject.getSameLoggerObject().getProcessManager().inputStream() : SameLoggerObject.getSameLoggerObject().getProcessManager().errorStream());
            } catch (final IOException exception) {
                SameLoggerObject.getSameLoggerObject().getLogger().warning(exception.getMessage());
            }
        } else {
            System.exit(-1);
        }
    }

    private boolean checkIsJarFile(final String... command){
        for (String strings: command) {
            if (strings.contains("-jar") || strings.contains("java") || strings.contains(".jar")){
                return true;
            }
        }
        return false;
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
        SameLoggerObject.getSameLoggerObject().getLogger().info(SameLoggerObject.getSameLoggerObject().getLogger().getPOMInformation(Logger.PomAllocation.DESCRIPTION).toString()+"\n");
        SameLoggerObject.getSameLoggerObject().getLogger().info("Developer: "+SameLoggerObject.getSameLoggerObject().getLogger().getPOMInformation(Logger.PomAllocation.DEVELOPERS).toString());
        SameLoggerObject.getSameLoggerObject().getLogger().info("ArtifactID: "+SameLoggerObject.getSameLoggerObject().getLogger().getPOMInformation(Logger.PomAllocation.ARTIFACT_ID).toString());
        SameLoggerObject.getSameLoggerObject().getLogger().info("Version: "+SameLoggerObject.getSameLoggerObject().getLogger().getPOMInformation(Logger.PomAllocation.VERSION).toString());
        SameLoggerObject.getSameLoggerObject().getLogger().info("GroupID: "+SameLoggerObject.getSameLoggerObject().getLogger().getPOMInformation(Logger.PomAllocation.GROUP_ID).toString());
        SameLoggerObject.getSameLoggerObject().getLogger().info("Github: "+SameLoggerObject.getSameLoggerObject().getLogger().getPOMInformation(Logger.PomAllocation.ORGANISATION).toString()+"\n");
    }

    public UUID getUuid() {
        return uuid;
    }
}
