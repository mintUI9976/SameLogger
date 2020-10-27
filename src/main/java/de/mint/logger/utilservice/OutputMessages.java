package de.mint.logger.utilservice;

public class OutputMessages {

    final String executeSuccessful = "The program was started successfully!";
    final String executeNoSuccessful = "The program could not be executed!";
    final String poweredDown = "The logger is now powered down!";
    final String processTerminated = "The process will be terminated!";
    final String processNeverStarted = "The process was never started!";
    final String pomInformationNoAvailable = "No %allocation% available!";
    final String fileSavedSuccessful = "The file '%fileName%' was saved successfully!";
    final String folderCreatedSuccessful = "The folder '%folderName%' was successfully created!";
    final String folderNoCreatedSuccessful = "Error the folder '%folderName%' could not be created!";
    final String fileCreatedSuccessful = "The file '%fileName%' was successfully created!";
    final String fileNoCreatedSuccessful = "Error the file '%fileName%' could not be created!";
    final String folderFileInitialisationSuccessful = "The folder and file's initialization was successful!";
    final String processIsInputStream = "The opened stream is an InputStream!";
    final String processIsErrorStream = "The opened stream is an ErrorStream!";

    public String getProcessIsInputStream() {
        return processIsInputStream;
    }

    public String getProcessIsErrorStream() {
        return processIsErrorStream;
    }

    public String getExecuteSuccessful() {
        return executeSuccessful;
    }

    public String getExecuteNoSuccessful() {
        return this.executeNoSuccessful;
    }

    public String getPoweredDown() {
        return this.poweredDown;
    }

    public String getProcessTerminated() {
        return this.processTerminated;
    }

    public String getProcessNeverStarted() {
        return this.processNeverStarted;
    }

    public String getPomInformationNoAvailable() {
        return this.pomInformationNoAvailable;
    }

    public String getFileSavedSuccessful() {
        return this.fileSavedSuccessful;
    }

    public String getFolderCreatedSuccessful() {
        return this.folderCreatedSuccessful;
    }

    public String getFolderNoCreatedSuccessful() {
        return this.folderNoCreatedSuccessful;
    }

    public String getFileCreatedSuccessful() {
        return this.fileCreatedSuccessful;
    }

    public String getFileNoCreatedSuccessful() {
        return this.fileNoCreatedSuccessful;
    }

    public String getFolderFileInitialisationSuccessful() {
        return this.folderFileInitialisationSuccessful;
    }
}
