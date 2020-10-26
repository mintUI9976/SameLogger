package de.mint.logger.utilservice;

import de.mint.logger.objectservice.SameLoggerObject;
import org.apache.maven.model.*;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;
import java.util.Calendar;

public class Logger {

    private final Calendar calendar = Calendar.getInstance();
    private final MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
    private final StringBuilder stringBuilder = new StringBuilder();

    public void info(final String message) {
        System.out.println("|" + Level.INFO.name() + "|" + this.calendar.getTime() + " | " + message);
    }

    public void warning(final String message) {
        System.out.println("|" + Level.WARNING.name() + "|" + this.calendar.getTime() + " | " + message);
    }

    public String stringRework(final String message) {
        return "|" + Level.LOG.name() + "|" + this.calendar.getTime() + " | " + message;
    }

    public Object getPOMInformation(final PomAllocation pomAllocation) {
        try {
            final Model model = this.mavenXpp3Reader.read(SameLoggerObject.getSameLoggerObject().getJarHandler().getPomFromJar());
            switch (pomAllocation) {
                case VERSION:
                    return model.getVersion() != null ? model.getVersion() : "No version available";
                case URL:
                    return model.getUrl() != null ? model.getUrl() : "No url available";
                case DEVELOPERS:
                    if (model.getDevelopers() != null) {
                        for (final Developer developer : model.getDevelopers()) {
                            this.stringBuilder.append(developer.getId());
                        }
                        return this.stringBuilder.toString();
                    } else {
                        return "No developer available";
                    }
                case LICENSE:
                    if (model.getLicenses() != null) {
                        for (final License license : model.getLicenses()) {
                            this.stringBuilder.append(license.getComments());
                        }
                        return this.stringBuilder.toString();
                    } else {
                        return "No license available";
                    }
                case ID:
                    return model.getId() != null ? model.getId() : "No id available";
                case GROUP_ID:
                    return model.getGroupId() != null ? model.getGroupId() : "No group-id available";
                case DESCRIPTION:
                    return model.getDescription() != null ? model.getDescription() : "No description available";
                case DEPENDENCIES:
                    if (model.getDependencies() != null) {
                        for (final Dependency dependency : model.getDependencies()) {
                            this.stringBuilder.append(dependency.getGroupId()).append(dependency.getArtifactId()).append(dependency.getVersion());
                        }
                        return this.stringBuilder.toString();
                    } else {
                        return "No dependency available";
                    }
                case ORGANISATION:
                    if (model.getOrganization() != null) {
                        return model.getOrganization().getName();
                    } else {
                        return "No organization available";
                    }
                case REPOSITORIES:
                    if (model.getRepositories() != null) {
                        for (final Repository repository : model.getRepositories()) {
                            this.stringBuilder.append(repository.getName()).append(repository.getUrl());
                        }
                        return this.stringBuilder.toString();
                    } else {
                        return "No repository available";
                    }
                case ARTIFACT_ID:
                    return model.getArtifactId() != null ? model.getArtifactId() : "No artifact-id available";
            }
            return null;
        } catch (final XmlPullParserException | IOException e) {
            SameLoggerObject.getSameLoggerObject().getLogger().warning(e.getMessage());
            return null;
        }
    }

    public enum PomAllocation {
        VERSION,
        DEVELOPERS,
        ID,
        URL,
        GROUP_ID,
        LICENSE,
        DESCRIPTION,
        ORGANISATION,
        DEPENDENCIES,
        REPOSITORIES,
        ARTIFACT_ID,
    }

    public enum Level {
        INFO,
        WARNING,
        LOG,
    }

}
