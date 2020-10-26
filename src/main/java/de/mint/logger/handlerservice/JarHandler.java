package de.mint.logger.handlerservice;

import de.mint.logger.mainservice.SameLogger;
import de.mint.logger.objectservice.SameLoggerObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarHandler {

    private JarURLConnection getJarUrlConnection(final String path) throws IOException {
        final File file = new File(SameLogger.class.getProtectionDomain().getCodeSource().getLocation().getPath()); //get current file location
        final String urlPath = "jar:file:" + (System.getProperty("os.name").toLowerCase().contains("windows") ? "/" : "") + file.getAbsolutePath() + "!/" + path;
        final URL url = new URL(urlPath); //load url
        return (JarURLConnection) url.openConnection(); //return java-url-connection
    }

    public InputStream getPomFromJar() {
        try {
            final JarURLConnection jarURLConnection = this.getJarUrlConnection("pom.xml");
            final JarFile jarfile = jarURLConnection.getJarFile();
            final JarEntry jarEntry = jarURLConnection.getJarEntry();
            return jarfile.getInputStream(jarEntry);
        } catch (final IOException exception) {
            SameLoggerObject.getSameLoggerObject().getLogger().warning(exception.getMessage());
        }
        return null;
    }
}
