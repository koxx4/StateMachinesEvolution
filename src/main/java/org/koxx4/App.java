package org.koxx4;

import org.koxx4.gui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main( String[] args ) {
        runApplication();
    }

    public static void runApplication(){
        SwingUtilities.invokeLater(() -> {
            try{
                Logger logger = LoggerFactory.getLogger("Startup");
                logger.debug("Creating JFrame");
                var frame = new MainFrame("StateMachines Evolution");
                logger.debug("Created object of {} successfully", frame.getClass());
            } catch (HeadlessException e) {
                e.printStackTrace();
            }
        });
    }

}
