package ui;
import exceptions.ArrayIsNotSortedException;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.ArrayTabulatedFunctionFactory;

//import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.ExceptionMessage.showError;

public class TabulatedFunctionUI extends JDialog {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField pointsTextField;
    private JButton createButton;
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionUI(OperationsWindow operationsWindow, TabulatedFunctionFactory factory) {
        super(operationsWindow, "Создание функции на основе массивов", Dialog.ModalityType.APPLICATION_MODAL);
        setSize(400, 300);
        setLocationRelativeTo(operationsWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

// Создаем компоненты окна
        JPanel inputPanel = new JPanel();
        pointsTextField = new JTextField(10);
        JButton inputVal = new JButton("ебашь");



        inputPanel.add(new JLabel("сколько нада:"));
        inputPanel.add(pointsTextField);
        inputPanel.add(inputVal);

        // Создаем таблицу для ввода значений x и y
        tableModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);//прокруч если много

        inputVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputValues();
            }
        });

        JPanel resultPanel = new JPanel();
        JButton createFunc = new JButton("Создать функцию");

        resultPanel.add(createFunc);

        createFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTabulatedFunction(operationsWindow, factory);
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);;

        setVisible(true);
    }



    private void inputValues() {
        try {
            int numberOfPoints = Integer.parseInt(pointsTextField.getText());
            if (numberOfPoints <= 0) {
                showError("Неверный Ввод. Пожалуйста введите ЧИСЛО > 0.");
                return;
            }

            for (int i = 0; i < numberOfPoints; i++) {
                Object[] rowData = new Object[2];
                rowData[0] = JOptionPane.showInputDialog("х" + (i + 1));
                rowData[1] = JOptionPane.showInputDialog("у" + (i + 1));
                tableModel.addRow(rowData);
            }


        } catch (NumberFormatException ex) {
            showError("Неверный Ввод. Пожалуйста введите ЦЕЛОЕ ЧИСЛО.");
        }
    }

    private void createTabulatedFunction(OperationsWindow operationsWindow, TabulatedFunctionFactory newFactory) {
        this.factory = newFactory;
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = new double[tableModel.getRowCount()];
        double[] yValues = new double[tableModel.getRowCount()];

        try {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                try {
                    xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                    yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
                } catch (NumberFormatException ex) {
                    showError("Неверный Ввод. Пожалуйста введите в " + (i+1) + " строке ЧИСЛА с плавающей точкой");
                }
            }
            TabulatedFunction func = factory.create(xValues, yValues);
            operationsWindow.setTabulatedFunc(func);
            System.out.println("Tabulated Function created: " + func);
            dispose();
        } catch (ArrayIsNotSortedException ex) {
            showError("Неверный Ввод. Значения x должны быть расположены по возрастанию.");
        }

    }




















/*    public static void main(String[] args) {

        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);


        try {
            UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }


            SwingUtilities.invokeLater(() -> {
                new TabulatedFunctionUI().setVisible(true);
            });
    }*/
}
