package org.koxx4.gui;

import org.koxx4.decoding.MachineDecoder;
import org.koxx4.decoding.MooreMachineDecoder;
import org.koxx4.syntax.formating.SimpleStateEquationFormatter;
import org.koxx4.syntax.formating.SyntaxFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class CreateMachinePanel extends JPanel {

    private final Logger logger;
    private final LayoutManager layoutManager;
    private final JLabel inputDescription;
    private final JLabel prettyEquationArea;
    private final JLabel parsingErrorMsgLabel;
    private final JTextField equationArea;
    private final JButton drawButton;
    private final GraphArea graphArea;
    private final SyntaxFormatter syntaxFormatter;
    private final JScrollPane prettyEquationScrollPane;
    private final JScrollPane graphAreaScrollPane;
    private final JScrollPane parsingErrorMsgScrollPane;

    public CreateMachinePanel() {
        super();

        this.logger = LoggerFactory.getLogger(CreateMachinePanel.class);
        this.syntaxFormatter = new SimpleStateEquationFormatter();
        this.layoutManager = new GridBagLayout();
        this.inputDescription = new JLabel("Enter your equation:");
        this.prettyEquationArea = new JLabel();
        this.parsingErrorMsgLabel = new JLabel();
        this.equationArea = new JTextField();
        this.drawButton = new JButton("Draw graph");
        this.graphArea = new GraphArea();
        this.prettyEquationScrollPane = new JScrollPane(prettyEquationArea);
        this.graphAreaScrollPane = new JScrollPane(graphArea);
        this.parsingErrorMsgScrollPane = new JScrollPane(parsingErrorMsgLabel);

        initializeComponentsProperties();
        initializeLayout();
        initializeActionListeners();
    }

    private void initializeComponentsProperties(){
        this.inputDescription.setForeground(Color.LIGHT_GRAY);
        this.inputDescription.setFont(new Font("Roboto Mono", Font.PLAIN, 15));

        this.parsingErrorMsgLabel.setForeground(new Color(255, 61, 61));
        this.parsingErrorMsgLabel.setFont(new Font("Roboto Mono", Font.PLAIN, 14));

        this.equationArea.setPreferredSize(new Dimension(500,30));
        this.equationArea.setFont(new Font("Roboto Mono", Font.PLAIN, 14));

        this.prettyEquationArea.setForeground(Color.BLACK);
        this.prettyEquationArea.setBackground(new Color(220,220,220));
        this.prettyEquationArea.setOpaque(true);
        this.prettyEquationArea.setPreferredSize(new Dimension(2000,30));
        this.prettyEquationArea.setFont(new Font("Roboto Mono", Font.PLAIN, 18));

        this.parsingErrorMsgScrollPane.setPreferredSize(new Dimension(500, 60));
        this.parsingErrorMsgScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        this.parsingErrorMsgScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.prettyEquationScrollPane.setPreferredSize(new Dimension(500, 60));
        this.prettyEquationScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.graphAreaScrollPane.setPreferredSize(new Dimension(400, 400));
        this.graphAreaScrollPane.setMinimumSize(new Dimension(300, 300));
        this.graphAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.graphAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setBackground(Color.DARK_GRAY);
    }

    private void initializeActionListeners(){
        this.drawButton.addActionListener(this::drawGraph);

        this.equationArea.addKeyListener(new KeyAdapter() {
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

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                MachineDecoder decoder = new MooreMachineDecoder();
                try {
                    decoder.decodeFromEquation(equationArea.getText());
                } catch (Exception exception){
                    parsingErrorMsgLabel.setText(exception.getMessage());
                    parsingErrorMsgLabel.setVisible(true);
                }
                return null;
            }

            @Override
            protected void done() {
                super.done();
            }
        }.execute();

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
        add(prettyEquationScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 10;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(parsingErrorMsgScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 10;
        gbc.gridheight = 10;
        gbc.fill = GridBagConstraints.BOTH;
        add(graphAreaScrollPane, gbc);

        gbc.gridheight = 1;
    }

}
