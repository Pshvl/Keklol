package ui;

import javax.swing.*;

import operations.DifferentialOperator;
import operations.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.*;

public class DifferentialWindow extends JDialog {
    private final JTextField initialFunctionField;
    private JLabel resultLabel;
    public DifferentialWindow(MainWindow mainWindow) {
        JDialog differentialWindow = new JDialog(mainWindow, "Настройки", Dialog.ModalityType.APPLICATION_MODAL);
        JLabel settings = new JLabel("Выберите тип создаваемой функции:");
        differentialWindow.setSize(300, 300);
        differentialWindow.setLocationRelativeTo(mainWindow);
        differentialWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

// Создание панели
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        differentialWindow.add(panel);


        // Создание левой области
        JLabel initialFunctionLabel = new JLabel("Initial Function:");
        initialFunctionField = new JTextField(10);
        JPanel leftPanel = new JPanel();
        leftPanel.add(initialFunctionLabel);
        leftPanel.add(initialFunctionField);
        panel.add(leftPanel, BorderLayout.WEST);
        // Создание правой области
        resultLabel = new JLabel("Result:");
        JPanel rightPanel = new JPanel();
        rightPanel.add(resultLabel);
        panel.add(rightPanel, BorderLayout.EAST);


        // Создание кнопки Compute
        JButton computeButton = new JButton("Compute");
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeDerivative();
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(computeButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        differentialWindow.setVisible(true);
        differentialWindow.setVisible(true);
    }

    private void computeDerivative() {
        try {            // Получение значения начальной функции
            String initialFunction = initialFunctionField.getText();
            // TODO: Реализуйте здесь логику вычисления производной табулированной функции
            // Обновление результата в правой области
            resultLabel.setText("Result: ");
        } catch (Exception ex) {
            resultLabel.setText("Error: Invalid function");
        }



    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }}

