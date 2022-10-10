package rfe.bsu.SikolenkoMa.laba2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;



public class Main {

    @SuppressWarnings("serial")
    public static class MainFrame extends JFrame{
        private static final int WIDTH = 700;
        private static final int HEIGHT = 500;

        private JTextField textFieldX;
        private JTextField textFieldY;

        private JTextField textFieldZ;

        private JTextField textFieldResult;

        private ButtonGroup radioButtons = new ButtonGroup();
        private Box hboxFormulaType = Box.createHorizontalBox();

        private int formulaId = 1;

        private Box FormulaBox = Box.createHorizontalBox();
        private JLabel IamgeLable = new JLabel();

        private int memoryID = 0;
        private ButtonGroup memoryButtons = new ButtonGroup();
        private Box Memorybox = Box.createHorizontalBox();
        private Vector<JTextField> TextMemory = new Vector<JTextField>();


        public Double calculate1(Double x, Double y, Double z) {
            return Math.sin(Math.log(y) + Math.sin(Math.PI * Math.pow(y, 2))) *
                    Math.pow(Math.pow(x, 2) + Math.sin(z) + Math.exp(Math.cos(z)), 0.25);
        }

        public Double calculate2(Double x, Double y, Double z) {
            return Math.pow((Math.cos(Math.exp(x)) + Math.pow(Math.log(1 + Math.pow(y, 2)), 2) +
                    Math.pow(Math.exp(Math.cos(x)) + Math.pow(Math.sin(Math.PI * z), 2), 0.5) +
                    Math.pow(x, -0.5) + Math.cos(Math.pow(y, 2))), Math.sin(z));
        }


        private void addFormulaRadioButton(String buttonName, final int formulaId) {
            JRadioButton button = new JRadioButton(buttonName);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {

                    MainFrame.this.formulaId = formulaId;
                    String path = ".\\src\\rfe\\bsu\\SikolenkoMa\\laba2\\Func_" + String.valueOf(formulaId) + ".bmp";

                    //System.out.println(path);

                    File file = new File(path);
                    Image img = null;
                    try {
                        img = ImageIO.read(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    IamgeLable.setIcon(new ImageIcon(img));

                }
            });
            radioButtons.add(button);
            hboxFormulaType.add(button);
        }

        private void addMemoryRadioButton(String buttonname, final int ID){
            JRadioButton button = new JRadioButton(buttonname);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {

                    MainFrame.this.memoryID = ID;
                }
            });
            memoryButtons.add(button);
            Memorybox.add(button);
        }


        public MainFrame() throws IOException {
            super("Formulas counting");
            setSize(WIDTH, HEIGHT);
            Toolkit kit = Toolkit.getDefaultToolkit();

            setLocation((kit.getScreenSize().width - WIDTH)/2,
                    (kit.getScreenSize().height - HEIGHT)/2);

            hboxFormulaType.add(Box.createHorizontalGlue());
            addFormulaRadioButton("Formula 1", 1);
            addFormulaRadioButton("Formula 2", 2);
            radioButtons.setSelected(
                    radioButtons.getElements().nextElement().getModel(), true);
            hboxFormulaType.add(Box.createHorizontalGlue());
            hboxFormulaType.setBorder(
                    BorderFactory.createLineBorder(Color.YELLOW));

            String path = ".\\src\\rfe\\bsu\\SikolenkoMa\\laba2\\Func_" + String.valueOf(formulaId) + ".bmp";
            File file = new File(path);
            Image img = null;
            try {
                img = ImageIO.read(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            IamgeLable = new JLabel(new ImageIcon(img));

            FormulaBox.add(Box.createHorizontalGlue());
            FormulaBox.add(IamgeLable);
            FormulaBox.add(Box.createHorizontalStrut(10));
            FormulaBox.add(Box.createHorizontalGlue());
            FormulaBox.setBorder(BorderFactory.createLineBorder(Color.BLUE));

            JLabel labelForX = new JLabel("X:");
            textFieldX = new JTextField("0", 10);
            textFieldX.setMaximumSize(textFieldX.getPreferredSize());
            JLabel labelForY = new JLabel("Y:");
            textFieldY = new JTextField("0", 10);
            textFieldY.setMaximumSize(textFieldY.getPreferredSize());

            JLabel labelForZ = new JLabel("Z:");
            textFieldZ = new JTextField("0", 10);
            textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

            Box hboxVariables = Box.createHorizontalBox();
            hboxVariables.setBorder(
                    BorderFactory.createLineBorder(Color.RED));
            hboxVariables.add(Box.createHorizontalGlue());
            hboxVariables.add(labelForX);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldX);
            hboxVariables.add(Box.createHorizontalStrut(100));
            hboxVariables.add(labelForY);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldY);
            hboxVariables.add(Box.createHorizontalStrut(100));
            hboxVariables.add(labelForZ);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldZ);

            hboxVariables.add(Box.createHorizontalGlue());

            JLabel labelForResult = new JLabel("Result:");
            textFieldResult = new JTextField("0", 10);
            textFieldResult.setMaximumSize(
                    textFieldResult.getPreferredSize());
            Box hboxResult = Box.createHorizontalBox();
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.add(labelForResult);
            hboxResult.add(Box.createHorizontalStrut(10));
            hboxResult.add(textFieldResult);
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

            JButton buttonCalc = new JButton("Count");
            buttonCalc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    try {
                        Double x = Double.parseDouble(textFieldX.getText());
                        Double y = Double.parseDouble(textFieldY.getText());
                        Double z = Double.parseDouble(textFieldZ.getText());
                        Double result;
                        if (formulaId==1)
                            result = calculate1(x, y, z);
                        else
                            result = calculate2(x, y, z);
                        textFieldResult.setText(result.toString());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Error in floating number!", "Error numbers formula",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            JButton buttonReset = new JButton("Clear");
            buttonReset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    textFieldX.setText("0");
                    textFieldY.setText("0");
                    textFieldZ.setText("0");
                    textFieldResult.setText("0");
                }
            });
            Box hboxButtons = Box.createHorizontalBox();
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.add(buttonCalc);
            hboxButtons.add(Box.createHorizontalStrut(30));
            hboxButtons.add(buttonReset);
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.setBorder(
                    BorderFactory.createLineBorder(Color.GREEN));


            Memorybox.add(Box.createHorizontalGlue());
            addMemoryRadioButton("Mem1", 0);
            Memorybox.add(Box.createHorizontalStrut(150));
            addMemoryRadioButton("Mem2", 1);
            Memorybox.add(Box.createHorizontalStrut(150));
            addMemoryRadioButton("Mem3", 2);
            memoryButtons.setSelected(
                    memoryButtons.getElements().nextElement().getModel(), true);
            Memorybox.add(Box.createHorizontalGlue());
            Memorybox.setBorder(
                    BorderFactory.createLineBorder(Color.BLACK));


            Vector<JLabel> MemoryLables = new Vector<JLabel>();
            MemoryLables.add(0, new JLabel("Mem1"));
            MemoryLables.get(0).setMaximumSize(MemoryLables.get(0).getPreferredSize());
            MemoryLables.add(1, new JLabel("Mem2"));
            MemoryLables.get(1).setMaximumSize(MemoryLables.get(1).getPreferredSize());
            MemoryLables.add(2, new JLabel("Mem3"));
            MemoryLables.get(2).setMaximumSize(MemoryLables.get(2).getPreferredSize());

            TextMemory.add(0, new JTextField("0", 10));
            TextMemory.get(0).setMaximumSize(TextMemory.get(0).getPreferredSize());
            TextMemory.add(1, new JTextField("0", 10));
            TextMemory.get(1).setMaximumSize(TextMemory.get(1).getPreferredSize());
            TextMemory.add(2, new JTextField("0", 10));
            TextMemory.get(2).setMaximumSize(TextMemory.get(2).getPreferredSize());
            Box TextMemoryBox = Box.createHorizontalBox();
            TextMemoryBox.setBorder(
                    BorderFactory.createLineBorder(Color.RED));
            for(int i = 0; i < 3; i++) {
                TextMemoryBox.add(Box.createHorizontalGlue());
                TextMemoryBox.add(MemoryLables.get(i));
                TextMemoryBox.add(Box.createHorizontalStrut(10));
                TextMemoryBox.add(TextMemory.get(i));
                TextMemoryBox.add(Box.createHorizontalStrut(50));
            }
            hboxVariables.add(Box.createHorizontalGlue());

            JButton MplusButton = new JButton("M+");
            MplusButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    Double num = Double.parseDouble(textFieldResult.getText());
                    num += Double.parseDouble(TextMemory.get(memoryID).getText());
                    TextMemory.get(memoryID).setText(String.valueOf(num));
                }
            });

            JButton MCButton = new JButton("MC");
            MCButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    TextMemory.get(memoryID).setText("0");
                }
            });


            Box MButtonBox = Box.createHorizontalBox();
            MButtonBox.add(Box.createHorizontalGlue());
            MButtonBox.add(MplusButton);
            MButtonBox.add(Box.createHorizontalStrut(30));
            MButtonBox.add(MCButton);
            MButtonBox.add(Box.createHorizontalGlue());
            MButtonBox.setBorder(
                    BorderFactory.createLineBorder(Color.BLACK));


            Box contentBox = Box.createVerticalBox();
            contentBox.add(Box.createVerticalGlue());
            contentBox.add(hboxFormulaType);
            contentBox.add(FormulaBox);
            contentBox.add(hboxVariables);
            contentBox.add(hboxResult);
            contentBox.add(hboxButtons);
            contentBox.add(Memorybox);
            contentBox.add(TextMemoryBox);
            contentBox.add(MButtonBox);
            contentBox.add(Box.createVerticalGlue());
            getContentPane().add(contentBox, BorderLayout.CENTER);
        }

    }

    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
