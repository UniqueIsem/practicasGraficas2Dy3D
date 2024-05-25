package practicasPrimerParcial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

    JFrame frame;
    JTextField txt;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
    Font myFont = new Font("Mono", Font.BOLD, 30);
    double num1 = 0, num2 = 0, res = 0;
    char operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        txt = new JTextField();
        txt.setBounds(50, 25, 300, 50);
        txt.setFont(myFont);
        txt.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("<--");
        clrButton = new JButton("C");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setFont(myFont);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setFont(myFont);
        }

        
        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(40, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(txt);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                txt.setText(txt.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            txt.setText(txt.getText().concat("."));
        } else if (e.getSource() == addButton) {
            num1 = Double.parseDouble(txt.getText());
            operator = '+';
            txt.setText("");
        } else if (e.getSource() == subButton) {
            num1 = Double.parseDouble(txt.getText());
            operator = '-';
            txt.setText("");
        } else if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(txt.getText());
            operator = '*';
            txt.setText("");
        } else if (e.getSource() == divButton) {
            num1 = Double.parseDouble(txt.getText());
            operator = '/';
            txt.setText("");
        } else if (e.getSource() == equButton) {
            num2 = Double.parseDouble(txt.getText());
            switch(operator){
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num1 - num2;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num1 / num2;
                    break;
            }
            txt.setText(String.valueOf(res));
            num1 = res;
        } else if (e.getSource() == clrButton) {
            txt.setText("");
        } else if (e.getSource() == delButton) {
            String string = txt.getText();
            txt.setText("");
            for (int i = 0; i < string.length() - 1 ; i++) {
                txt.setText(txt.getText() + string.charAt(i));
            }
        } else if (e.getSource() == negButton) {
            double temp = Double.parseDouble(txt.getText());
            temp *= -1;
            txt.setText(String.valueOf(temp));
        }

    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

}
