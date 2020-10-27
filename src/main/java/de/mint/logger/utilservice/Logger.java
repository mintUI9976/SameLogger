package de.mint.logger.utilservice;

import de.mint.logger.objectservice.SameLoggerObject;
import org.apache.maven.model.*;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;

public class Logger {

    private final MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
    private final StringBuilder stringBuilder = new StringBuilder();

    public void info(final String message) {
        System.out.println("|" + level.INFO.name() + "|" + SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().getDate() + " | " + message);
    }

    public void warning(final String message) {
        System.out.println("|" + level.WARNING.name() + "|" + SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().getDate() + " | " + message);
    }

    public String stringRework(final String message) {
        return "|" + level.LOG.name() + "|" + SameLoggerObject.getSameLoggerObject().getSameLoggerBootStrap().getDate() + " | " + message;
    }

    public Object getPOMInformation(final pomAllocation pomAllocation) {
        try {
            final Model model = this.mavenXpp3Reader.read(SameLoggerObject.getSameLoggerObject().getJarHandler().getPomFromJar());
            switch (pomAllocation) {
                case VERSION:
                    return model.getVersion() != null ? model.getVersion() : SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                case URL:
                    return model.getUrl() != null ? model.getUrl() : SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                case DEVELOPERS:
                    if (model.getDevelopers() != null) {
                        for (final Developer developer : model.getDevelopers()) {
                            this.stringBuilder.append(developer.getId());
                        }
                        return this.stringBuilder.toString();
                    } else {
                        return SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                    }
                case LICENSE:
                    if (model.getLicenses() != null) {
                        for (final License license : model.getLicenses()) {
                            this.stringBuilder.append(license.getComments());
                        }
                        return this.stringBuilder.toString();
                    } else {
                        return SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                    }
                case ID:
                    return model.getId() != null ? model.getId() : SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                case GROUP_ID:
                    return model.getGroupId() != null ? model.getGroupId() : SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                case DESCRIPTION:
                    return model.getDescription() != null ? model.getDescription() : SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                case DEPENDENCIES:
                    if (model.getDependencies() != null) {
                        for (final Dependency dependency : model.getDependencies()) {
                            this.stringBuilder.append(dependency.getGroupId()).append(dependency.getArtifactId()).append(dependency.getVersion());
                        }
                        return this.stringBuilder.toString();
                    } else {
                        return SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                    }
                case ORGANISATION:
                    if (model.getOrganization() != null) {
                        return model.getOrganization().getName();
                    } else {
                        return SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                    }
                case REPOSITORIES:
                    if (model.getRepositories() != null) {
                        for (final Repository repository : model.getRepositories()) {
                            this.stringBuilder.append(repository.getName()).append(repository.getUrl());
                        }
                        return this.stringBuilder.toString();
                    } else {
                        return SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
                    }
                case ARTIFACT_ID:
                    return model.getArtifactId() != null ? model.getArtifactId() : SameLoggerObject.getSameLoggerObject().getOutputMessages().getPomInformationNoAvailable().replace("%allocation%" , pomAllocation.name());
            }
            return null;
        } catch (final XmlPullParserException | IOException e) {
            SameLoggerObject.getSameLoggerObject().getLogger().warning(e.getMessage());
            return null;
        }
    }

    public enum pomAllocation {
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

    public enum level {
        INFO,
        WARNING,
        LOG,
    }

}
