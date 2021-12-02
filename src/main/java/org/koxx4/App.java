package org.koxx4;

import org.koxx4.gui.MainFrame;
import org.koxx4.utilities.AppPropertiesHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;

public class App {


    public static void main( String[] args ) {
        Logger logger = LoggerFactory.getLogger("App");
        logger.debug("Loading app properties");
        loadAppProperties();
        logger.debug("Successfully loaded app properties");

        logger.debug("Creating JFrame");
        runApplication();
        logger.debug("Successfully create JFrame");
    }

    public static void runApplication(){
        SwingUtilities.invokeLater(() -> {
            try{
                MainFrame frame = new MainFrame();
            } catch (HeadlessException e) {
                e.printStackTrace();
            }
        });
    }

    public static void loadAppProperties(){
        try {
            AppPropertiesHolder appPropertiesHolder = new AppPropertiesHolder(
                    new File(App.class.getResource("/app.properties").toURI())
            );
            appPropertiesHolder.loadProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
