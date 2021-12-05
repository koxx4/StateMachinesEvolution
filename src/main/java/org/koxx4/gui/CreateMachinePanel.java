package org.koxx4.gui;

import org.koxx4.utilities.SimpleStateEquationFormatter;
import org.koxx4.utilities.SyntaxFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class CreateMachinePanel extends JPanel {

    private final Logger logger;
    private final LayoutManager layoutManager;
    private final JLabel inputDescription;
    private final JLabel prettyEquationArea;
    private final JTextField equationArea;
    private final JButton drawButton;
    private final GraphArea graphArea;
    private final SyntaxFormatter syntaxFormatter;
    private String formattedEquation;
    private Font robotoMonoFont;

    public CreateMachinePanel() {
        super();

        initializeExternalFonts();

        this.logger = LoggerFactory.getLogger(CreateMachinePanel.class);
        this.syntaxFormatter = new SimpleStateEquationFormatter();
        this.layoutManager = new GridBagLayout();
        this.inputDescription = new JLabel("Enter your equation:");
        this.prettyEquationArea = new JLabel();
        this.equationArea = new JTextField();
        this.drawButton = new JButton("Draw graph");
        this.graphArea = new GraphArea();

        initializeComponentsProperties();
        initializeLayout();
        initializeActionListeners();
    }

    private void initializeComponentsProperties(){
        this.inputDescription.setForeground(Color.LIGHT_GRAY);
        this.inputDescription.setFont(new Font("Roboto Mono", Font.PLAIN, 15));

        this.equationArea.setPreferredSize(new Dimension(500,30));
        this.equationArea.setFont(new Font("Roboto Mono", Font.PLAIN, 14));

        this.prettyEquationArea.setForeground(Color.BLACK);
        this.prettyEquationArea.setBackground(new Color(220,220,220));
        this.prettyEquationArea.setOpaque(true);
        this.prettyEquationArea.setPreferredSize(new Dimension(2000,30));
        this.prettyEquationArea.setFont(new Font("Roboto Mono", Font.PLAIN, 18));

        this.setBackground(Color.DARK_GRAY);
    }

    private void initializeExternalFonts() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    new File(getClass().getResource("/fonts/roboto-mono.ttf").toURI())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeActionListeners(){
        this.drawButton.addActionListener(this::drawGraph);

        this.equationArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                new SwingWorker<String, Void>() {
                    @Override
                    protected String doInBackground() throws Exception {

                        return "<html>"
                                + syntaxFormatter.format(equationArea.getText())
                                + "</html>";
                    }

                    @Override
                    protected void done() {
                        try {
                            prettyEquationArea.setText(get());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();


            }
        });
    }

    private void drawGraph(ActionEvent actionEvent){
        this.logger.info("Trying to draw graph");
    }

    private void initializeLayout(){
        var gbc = new GridBagConstraints();
        setLayout(this.layoutManager);

        gbc.insets = new Insets(10,10,10,10);

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
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        var v = new JScrollPane(prettyEquationArea);
        v.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        v.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        v.setPreferredSize(new Dimension(500, 60));
        add(v, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 10;
        gbc.gridheight = 10;
        gbc.fill = GridBagConstraints.BOTH;
        add(graphArea, gbc);

        gbc.gridheight = 1;
    }

}
