//package server.oldFiles;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
//
//public class MainWindow {
//    JPanel mainPanel;
//    JPanel numbersPanel;
//    JPanel operationsPanel;
//    JPanel rightPanel;
//    JPanel bottomPanel;
//    JFrame frame;
//    JLabel label;
//
//    JTextField displayField;
//
//    List<JButton> numButtons;
//    List<JButton> operationButtons;
//
//    String operations = "+-*/";
//
//    BorderLayout borderLayout;
//    GridLayout gridLayout1;
//    GridLayout gridLayout2;
//    GridLayout rightGridLayout3;
//    BorderLayout bottomBorderLayout;
//
//
//
////
//
//    public MainWindow() {
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        this.mainPanel = new JPanel();
//        this.numbersPanel = new JPanel();
//        this.operationsPanel = new JPanel();
//        this.rightPanel = new JPanel();
//        this.bottomPanel = new JPanel();
//
//        this.borderLayout = new BorderLayout(1,1);
//        this.gridLayout1 = new GridLayout(0, 3);
//        this.gridLayout2 = new GridLayout(2,0);
//        this.rightGridLayout3 = new GridLayout(0,1);
//        this.bottomBorderLayout = new BorderLayout(1,1);
//
//
//        this.displayField = new JTextField(15);
//        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
//        this.frame = new JFrame("Calc");
//        this.numButtons = new ArrayList<>();
//        this.operationButtons = new ArrayList<>();
//        this.label = new JLabel("PIPISKA");
//        Font font1 = new Font("SansSerif", Font.BOLD, 20);
//        displayField.setFont(font1);
//
//
//        for (int i = 0; i < operations.length(); i++) {
//            operationButtons.add(new JButton(String.valueOf(operations.charAt(i))));
//
//        }
//
//        for (int i = 1; i < 10; i++) {
//            numButtons.add(new JButton(String.valueOf(i)));
//        }
//        numButtons.add(new JButton("0"));
//        numButtons.add(new JButton("."));
//
//        for (JButton numButton : numButtons) {
//            numbersPanel.add(numButton);
//        }
//
//        for (JButton operationButton : operationButtons) {
//            operationsPanel.add(operationButton);
//        }
//
//         JButton clear = new JButton("C");
//         JButton equalsButton = new JButton("=");
//         JButton percentButton = new JButton("%");
//
//        mainPanel.setLayout(borderLayout);
//        numbersPanel.setLayout(gridLayout1);
//        operationsPanel.setLayout(gridLayout2);
//        rightPanel.setLayout(rightGridLayout3);
//        bottomPanel.setLayout(bottomBorderLayout);
//
//        rightPanel.add(clear);
//        rightPanel.add(percentButton);
//        rightPanel.add(equalsButton);
//
//        bottomPanel.add("North", operationsPanel);
//        bottomPanel.add("South", label);
//
//        mainPanel.add("North", displayField);
//        mainPanel.add("Center", numbersPanel);
//        mainPanel.add("East", rightPanel);
//        mainPanel.add("South", bottomPanel);
//
//
//
//
//
//
//
//        frame.setContentPane(mainPanel);
//        frame.pack();
//        frame.setVisible(true);
//
////        CalculatorEngine calcEngine = new CalculatorEngine(this);
//        for (JButton numButton : numButtons) {
////            numButton.addActionListener(calcEngine);
//        }
//        for (JButton actionButton : operationButtons) {
////            actionButton.addActionListener(calcEngine);
//        }
//
//        for (JButton actionButton : operationButtons) {
//            actionButton.setFont((new Font("Arial", Font.BOLD, 15)));
//        }
//
////        clear.addActionListener(calcEngine);
////        equalsButton.addActionListener(calcEngine);
////        percentButton.addActionListener(calcEngine);
//
//
//        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
//    }
//}
