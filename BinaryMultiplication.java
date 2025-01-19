import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryMultiplication extends JFrame {
    private JTextField input1Field;
    private JTextField input2Field;
    private JLabel binary1Label;
    private JLabel binary2Label;
    private JLabel resultLabel;

    public BinaryMultiplication() {
        setTitle("Binary Multiplication Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel input1Label = new JLabel(" Enter first integer:");
        input1Field = new JTextField();
        JLabel input2Label = new JLabel(" Enter second integer:");
        input2Field = new JTextField();
        binary1Label = new JLabel(" First number in binary: ");
        binary2Label = new JLabel("Second number in binary: ");
        resultLabel = new JLabel("Result in binary and integer: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JButton calculateButton = new JButton("Multiply");
        calculateButton.addActionListener(new MultiplyAction());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields());

        add(input1Label);
        add(input1Field);
        add(input2Label);
        add(input2Field);
        add(binary1Label);
        add(binary2Label);
        add(calculateButton);
        add(clearButton);
        add(new JLabel("")); 
        add(resultLabel);
    }
    private void clearFields() {
        input1Field.setText("");
        input2Field.setText("");
        binary1Label.setText(" First number in binary: ");
        binary2Label.setText("Second number in binary: ");
        resultLabel.setText("Result in binary and integer: ");
    }
    private class MultiplyAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String input1 = input1Field.getText();
                String input2 = input2Field.getText();
                if (!isInteger(input1) || !isInteger(input2)) {
                    JOptionPane.showMessageDialog(null, "Please enter only valid integers.");
                    return;
                }
                int num1 = Integer.parseInt(input1);
                int num2 = Integer.parseInt(input2);
                String binary1 = convertToBinary(num1);
                String binary2 = convertToBinary(num2);
                binary1Label.setText(" First number in binary: " + binary1);
                binary2Label.setText("Second number in binary: " + binary2);
                int product = num1 * num2;
                String binaryProduct = convertToBinary(product);
                resultLabel.setText("Result in binary: " + binaryProduct + " | Result in integer: " + product);
            } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter only valid integers.");
            }
        }
    }
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private String convertToBinary(int number) {
        String binary = Integer.toBinaryString(number);
        if (number < 0) {
            int bitLength = 32; 
            return String.format("%" + bitLength + "s", binary).replace(' ', '0').substring(binary.length() - 8); 
        }
        return truncateBinary(binary);
    }
    private String truncateBinary(String binary) {
        if (binary.length() > 8) {
            return binary.substring(binary.length() - 8);
        }
        return binary;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BinaryMultiplication calculator = new BinaryMultiplication();
            calculator.setVisible(true);
        });
    }
}
