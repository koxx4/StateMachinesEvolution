package org.koxx4.gui;

import org.koxx4.utilities.AppPropertiesHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    private final AppPropertiesHolder appPropertiesHolder  = AppPropertiesHolder.getInstance();
    private JMenuBar menuBar;
    private final CreateMachinePanel createMachinePanel;
    private final SimulateMachinePanel simulateMachinePanel;
    private final AnalysePanel analysePanel;
    private JPanel activePanel;

    public MainFrame() throws HeadlessException {
        super(AppPropertiesHolder.getInstance().getMainWindowTitle());
        initializeFrameProperties();
        initializeMenuBar();

        createMachinePanel = new CreateMachinePanel();
        simulateMachinePanel = new SimulateMachinePanel();
        analysePanel = new AnalysePanel();
        setActivePanel(createMachinePanel);
    }

    private void initializeFrameProperties(){
        logger.info("Initializing frame properties");
        setVisible(true);
        setSize(500,500);
        setMinimumSize(new Dimension(300,300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void initializeMenuBar(){
        logger.info("Initializing menu bar");
        menuBar = new JMenuBar();
        JMenu modesMenu = new JMenu("Mode");
        JMenuItem createMachineModeMenuItem = new JMenuItem("Create machine");
        JMenuItem simulateMachineModeMenuItem = new JMenuItem("Simulate machine");
        JMenuItem analyseMachineModeMenuItem = new JMenuItem("Analyse machine");
        createMachineModeMenuItem.addActionListener(e -> setActivePanel(createMachinePanel));
        simulateMachineModeMenuItem.addActionListener(e -> setActivePanel(simulateMachinePanel));
        analyseMachineModeMenuItem.addActionListener(e -> setActivePanel(analysePanel));
        modesMenu.add(createMachineModeMenuItem);
        modesMenu.add(simulateMachineModeMenuItem);
        modesMenu.add(analyseMachineModeMenuItem);
        menuBar.add(modesMenu);
        this.add(menuBar, BorderLayout.NORTH);
    }

    private void setActivePanel(JPanel panel){
        if (this.activePanel == panel)
            return;

        if (this.activePanel == null)
            this.activePanel = panel;

        this.remove(activePanel);
        this.add(panel, BorderLayout.CENTER);
        this.activePanel = panel;
        this.validate();
        this.repaint();

        logger.info("Changed panel to {}", panel.toString());
    }

}
