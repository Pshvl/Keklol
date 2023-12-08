package ui;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.ArrayTabulatedFunctionFactory;

//import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabulatedFunctionWindow2 extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField pointsTextField;
    private JButton createButton;

    public TabulatedFunctionWindow2() {
        setTitle("ЖОПА СИСЬСКИ СРАТЬ КОРЗИНКА");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLocationRelativeTo(null);//по центру

// Создаем компоненты окна
        JPanel inputPanel = new JPanel();
        pointsTextField = new JTextField(10);
        createButton = new JButton("ебашь");



        inputPanel.add(new JLabel("сколько нада:"));
        inputPanel.add(pointsTextField);
        inputPanel.add(createButton);

        // Создаем таблицу для ввода значений x и y
        tableModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);//прокруч если много

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTabulatedFunction();
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createTabulatedFunction() {
        try {
            int numberOfPoints = Integer.parseInt(pointsTextField.getText());
            if (numberOfPoints <= 0) {
                showError("Please enter a valid number of points.");
                return;
            }

            tableModel.setRowCount(0);

            for (int i = 0; i < numberOfPoints; i++) {
                Object[] rowData = new Object[2];
                rowData[0] = JOptionPane.showInputDialog("х" + (i + 1));
                rowData[1] = JOptionPane.showInputDialog("у" + (i + 1));
                tableModel.addRow(rowData);
            }

            // Создаем табулированную функцию с использованием фабрики
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            double[] xValues = new double[numberOfPoints];
            double[] yValues = new double[numberOfPoints];

            for (int i = 0; i < numberOfPoints; i++) {
                xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            }

            TabulatedFunction tabulatedFunction = factory.create(xValues, yValues);
            System.out.println("Tabulated Function created: " + tabulatedFunction);

            // Закрываем окно
            dispose();
        } catch (NumberFormatException ex) {
            showError("Invalid input. Please enter a valid number.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }



















    public static void main(String[] args) {

        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);


        try {
            UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }



//        SwingUtilities.invokeLater(new Runnable() {
////            public void run() {
////                TabulatedFunctionWindow2 window = new TabulatedFunctionWindow2();
////                window.setVisible(true);
////            }
//        });

            SwingUtilities.invokeLater(() -> {
                new TabulatedFunctionWindow2().setVisible(true);
            });
    }
}
