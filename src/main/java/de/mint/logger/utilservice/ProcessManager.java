package de.mint.logger.utilservice;

import de.mint.logger.objectservice.SameLoggerObject;

import java.io.IOException;
import java.io.InputStream;

public class ProcessManager {

    private Process process;

    public void executeProcess(final String processCommand) {
        try {
            this.process = Runtime.getRuntime().exec(processCommand);
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

   /* public boolean isProcessAlive(){
        if (this.process.isAlive()) {
            SSHESameLoggerObject.getSSHESameLoggerObject().getLogger().log(Level.INFO, "The process '" + processCommand + "' is now executed");
            return true;
        } else {
            SSHESameLoggerObject.getSSHESameLoggerObject().getLogger().log(Level.INFO, "The process '" + processCommand + "' could not be executed");
            return false;
        }
    }*/

    public void killProcess() {
        if (this.process.isAlive()) {
            this.process.destroy();
            SameLoggerObject.getSameLoggerObject().getLogger().info("The process will be terminated");
        } else {
            SameLoggerObject.getSameLoggerObject().getLogger().warning("The process was never started");
        }
    }


    public void timeout(final long time) {
        try {
            Thread.sleep(time);
        } catch (final InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public InputStream inputStream() {
        return this.process.getInputStream();
    }

    public InputStream errorStream() {
        return this.process.getErrorStream();
    }


    public Process getProcess() {
        return this.process;
    }
}
