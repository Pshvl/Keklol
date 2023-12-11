package ui;

import javax.swing.*;
import functions.TabulatedFunction;
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



    private TabulatedFunction initialFunction;
    private TabulatedFunction differentiatedFunction;
    private TabulatedDifferentialOperator tabulatedDifferentialOperator;
    double[] xValues;
    double[] yValues;
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

    JButton createButton = new JButton("Ñîçäàòü");

        createButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TabulatedFunctionUI(xValues, yValues);
            initialFunction = (TabulatedFunction)TabulatedFunctionDatabase.database;

            Object[][] tableData = new Object[initialFunction.getCount()][2];
            for (int i = 0; i < initialFunction.getCount(); i++) {
                tableData[i][0] = initialFunction.getX(i);
                tableData[i][1] = initialFunction.getY(i);
            }


            String[] columnNames = {"X", "Y"};


            JTable table = new JTable(tableData, columnNames);


            JScrollPane scrollPane = new JScrollPane(table);


            add(scrollPane);
        }
    });



    JButton differentiateButton = new JButton("compute");
        panel.add(differentiateButton);
        differentiateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            differentiatedFunction = tabulatedDifferentialOperator.deriveSynchronously((TabulatedFunction)initialFunction);
            JLabel diffFunctionLabel = new JLabel("Äèôôåðåíöèðîâàííàÿ ôóíêöèÿ: ");
            add(diffFunctionLabel);

            Object[][] tableData = new Object[differentiatedFunction.getCount()][2];
            for (int i = 0; i < differentiatedFunction.getCount(); i++) {
                tableData[i][0] = differentiatedFunction.getX(i);
                tableData[i][1] = differentiatedFunction.getY(i);
            }


            String[] columnNames = {"X", "Y"};


            JTable table = new JTable(tableData, columnNames);


            JScrollPane scrollPane = new JScrollPane(table);


            add(scrollPane);
        }
    });



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }}

