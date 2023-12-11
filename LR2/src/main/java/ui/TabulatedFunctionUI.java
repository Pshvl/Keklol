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
    private JDialog tabulatedFuncWindow;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField pointsTextField;
    private JButton createButton;

    public TabulatedFunctionUI(Dialog operationsWindow, TabulatedFunctionFactory factory) {
        tabulatedFuncWindow = new JDialog(operationsWindow, "Создание функции на основе массивов", Dialog.ModalityType.APPLICATION_MODAL);
        tabulatedFuncWindow.setSize(400, 300);
        tabulatedFuncWindow.setLocationRelativeTo(operationsWindow);
        tabulatedFuncWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /*setTitle("ЖОПА СИСЬСКИ СРАТЬ КОРЗИНКА");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLocationRelativeTo(null);//по центру*/

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
                createTabulatedFunction();
            }
        });

        tabulatedFuncWindow.add(inputPanel, BorderLayout.NORTH);
        tabulatedFuncWindow.add(resultPanel, BorderLayout.SOUTH);
        tabulatedFuncWindow.add(scrollPane, BorderLayout.CENTER);;

        tabulatedFuncWindow.setVisible(true);
    }



    private void inputValues() {
        try {
            int numberOfPoints = Integer.parseInt(pointsTextField.getText());
            if (numberOfPoints <= 0) {
                showError("Неверный Ввод. Пожалуйста введите ЧИСЛО > 0.");
                return;
            }

            tableModel.setRowCount(0);

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

    private void createTabulatedFunction() {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = new double[tableModel.getRowCount()];
        double[] yValues = new double[tableModel.getRowCount()];

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            try {
            xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            } catch (NumberFormatException ex) {
                showError("Неверный Ввод. Пожалуйста введите в " + (i+1) + " строке ЧИСЛА с плавающей точкой");
            }
        }

        try {
            ArrayTabulatedFunction func = new ArrayTabulatedFunctionFactory().create(xValues, yValues);
            System.out.println("Tabulated Function created: " + func);
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
