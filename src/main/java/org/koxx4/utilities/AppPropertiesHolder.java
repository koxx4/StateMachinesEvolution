package org.koxx4.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppPropertiesHolder {

    private final File propertiesFile;
    private Properties properties;
    private static AppPropertiesHolder instance;

    public AppPropertiesHolder(File propertiesFile) {
        this.propertiesFile = propertiesFile;
        AppPropertiesHolder.instance = this;
    }

    public void loadProperties() throws IOException {
        this.properties = new Properties();
        this.properties.load(new FileReader(this.propertiesFile));
    }

    public String getMainWindowTitle(){
        return properties.getProperty("mainWindow.title");
    }

    public static AppPropertiesHolder getInstance() {
        return instance;
    }

}
