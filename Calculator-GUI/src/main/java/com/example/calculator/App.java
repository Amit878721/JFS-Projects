package com.example.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;

/**
 * Original calculator kept as a GUI application.
 * Added features do not replace the original ADD, SUBTRACT, MULTIPLY, DIV and EXIT flow.
 */
public class App implements ActionListener {
    JFrame frame;
    JPanel panel;
    JTextField t1, t2, tresult;
    JButton addBtn, subBtn, mulBtn, divBtn, exitBtn;

    // Added features
    JButton clearBtn, percentBtn, squareBtn, sqrtBtn, historyBtn;
    JTextArea historyArea;
    private final DecimalFormat format = new DecimalFormat("0.##########");

    public App() {
        frame = new JFrame("Simple Calculator");
        frame.setSize(650, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        JLabel l1 = new JLabel("First Number:");
        t1 = new JTextField(10);
        JLabel l2 = new JLabel("Second Number:");
        t2 = new JTextField(10);
        JLabel l3 = new JLabel("Result:");
        tresult = new JTextField(10);
        tresult.setEditable(false);

        addBtn = new JButton("ADD");
        subBtn = new JButton("SUBTRACT");
        mulBtn = new JButton("MULTIPLY");
        divBtn = new JButton("DIV");
        exitBtn = new JButton("EXIT");

        // New GUI features
        clearBtn = new JButton("CLEAR");
        percentBtn = new JButton("PERCENT %");
        squareBtn = new JButton("SQUARE");
        sqrtBtn = new JButton("SQRT");
        historyBtn = new JButton("HISTORY");
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setBorder(BorderFactory.createTitledBorder("Calculation History"));

        // Original controls retain their purpose and remain in the GUI.
        l1.setBounds(50, 30, 100, 25);
        t1.setBounds(160, 30, 150, 25);
        l2.setBounds(50, 70, 120, 25);
        t2.setBounds(160, 70, 150, 25);
        l3.setBounds(50, 110, 100, 25);
        tresult.setBounds(160, 110, 150, 25);

        addBtn.setBounds(50, 155, 100, 30);
        subBtn.setBounds(160, 155, 120, 30);
        mulBtn.setBounds(290, 155, 120, 30);
        divBtn.setBounds(420, 155, 100, 30);

        clearBtn.setBounds(50, 200, 100, 30);
        percentBtn.setBounds(160, 200, 120, 30);
        squareBtn.setBounds(290, 200, 120, 30);
        sqrtBtn.setBounds(420, 200, 100, 30);
        historyBtn.setBounds(50, 245, 120, 30);
        exitBtn.setBounds(180, 245, 100, 30);
        historyArea.setBounds(330, 245, 250, 170);

        addBtn.addActionListener(this);
        subBtn.addActionListener(this);
        mulBtn.addActionListener(this);
        divBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        percentBtn.addActionListener(this);
        squareBtn.addActionListener(this);
        sqrtBtn.addActionListener(this);
        historyBtn.addActionListener(this);

        panel.add(l1); panel.add(t1);
        panel.add(l2); panel.add(t2);
        panel.add(l3); panel.add(tresult);
        panel.add(addBtn); panel.add(subBtn);
        panel.add(mulBtn); panel.add(divBtn);
        panel.add(clearBtn); panel.add(percentBtn);
        panel.add(squareBtn); panel.add(sqrtBtn);
        panel.add(historyBtn); panel.add(exitBtn);
        panel.add(historyArea);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == exitBtn) {
            System.exit(0);
        }

        if (source == clearBtn) {
            t1.setText("");
            t2.setText("");
            tresult.setText("");
            return;
        }

        if (source == historyBtn) {
            JOptionPane.showMessageDialog(frame,
                    historyArea.getText().isBlank() ? "No calculations yet." : historyArea.getText(),
                    "Calculation History", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            double num1 = Double.parseDouble(t1.getText().trim());
            double num2 = t2.getText().trim().isEmpty() ? 0 : Double.parseDouble(t2.getText().trim());
            double result;
            String expression;

            if (source == addBtn) {
                result = num1 + num2;
                expression = format.format(num1) + " + " + format.format(num2) + " = " + format.format(result);
            } else if (source == subBtn) {
                result = num1 - num2;
                expression = format.format(num1) + " - " + format.format(num2) + " = " + format.format(result);
            } else if (source == mulBtn) {
                result = num1 * num2;
                expression = format.format(num1) + " × " + format.format(num2) + " = " + format.format(result);
            } else if (source == divBtn) {
                if (num2 == 0) {
                    tresult.setText("Error: Divide by 0");
                    return;
                }
                result = num1 / num2;
                expression = format.format(num1) + " ÷ " + format.format(num2) + " = " + format.format(result);
            } else if (source == percentBtn) {
                result = num1 * num2 / 100.0;
                expression = format.format(num1) + "% of " + format.format(num2) + " = " + format.format(result);
            } else if (source == squareBtn) {
                result = num1 * num1;
                expression = format.format(num1) + "² = " + format.format(result);
            } else if (source == sqrtBtn) {
                if (num1 < 0) {
                    tresult.setText("Error: Negative sqrt");
                    return;
                }
                result = Math.sqrt(num1);
                expression = "√" + format.format(num1) + " = " + format.format(result);
            } else {
                return;
            }

            tresult.setText(format.format(result));
            historyArea.append(expression + "\n");
        } catch (NumberFormatException ex) {
            tresult.setText("Invalid Input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
