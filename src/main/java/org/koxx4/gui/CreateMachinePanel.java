package org.koxx4.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class CreateMachinePanel extends JPanel {

    private final Logger logger;
    private final LayoutManager layoutManager;
    private final JLabel inputDescription;
    private final JTextField equationArea;
    private final JButton drawButton;
    private final GraphArea graphArea;

    public CreateMachinePanel() {
        super();

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    new File(getClass().getResource("/fonts/roboto-mono.ttf").toURI())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.logger = LoggerFactory.getLogger(CreateMachinePanel.class);
        this.layoutManager = new GridBagLayout();
        this.inputDescription = new JLabel("Enter your equation:");
        this.inputDescription.setForeground(Color.LIGHT_GRAY);
        this.inputDescription.setFont(Font.getFont("roboto-mono"));
        this.equationArea = new JTextField();
        this.equationArea.setPreferredSize(new Dimension(500,20));
        this.drawButton = new JButton("Draw graph");
        this.graphArea = new GraphArea();

        this.setBackground(Color.DARK_GRAY);
        initializeLayout();
        initializeActionListeners();
    }

    private void initializeActionListeners(){
        this.drawButton.addActionListener(this::drawGraph);
    }

    private void drawGraph(ActionEvent actionEvent){
        this.logger.info("Trying to draw graph");
    }

    private void initializeLayout(){
        var gbc = new GridBagConstraints();
        setLayout(this.layoutManager);

        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(inputDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 10;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(equationArea, gbc);

        gbc.gridx = 10;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(drawButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 10;
        gbc.gridheight = 10;
        gbc.fill = GridBagConstraints.BOTH;
        add(graphArea, gbc);

        gbc.gridheight = 1;
    }

}
