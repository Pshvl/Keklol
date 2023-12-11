package ui;
import io.FunctionsIO;
import operations.TabulatedFunctionOperationService;
import functions.TabulatedFunction;
//import functions.ArrayTabulatedFunction;
//import functions.factory.ArrayTabulatedFunctionFactory;
//import functions.factory.TabulatedFunctionFactory;
//import functions.factory.LinkedListTabulatedFunctionFactory;
//import java.io.*;
//import java.io.BufferedReader;
//import io.*;
//import operations.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Arrays;
//import java.io.ObjectOutputStream;
//

import java.io.IOException;
import java.io.BufferedOutputStream;
import javax.swing.JTable;
import javax.swing.*;
import java.awt.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.table.DefaultTableModel;

import static operations.TabulatedFunctionOperationService.Operation.*;
import static operations.TabulatedFunctionOperationService.Operation.divide;

public class OperationsWindow extends JDialog {
    private JDialog operationsWindow;
    private DefaultTableModel model1;
//    private DefaultTableModel model2;
//    private DefaultTableModel model3;

    public JTable table;
    private TabulatedFunctionUI functionUI1;
    private TabulatedFunctionUI functionUI2;
//    public JTable secondFunctionTable;
//    public JTable resultTable;
    private TabulatedFunctionOperationService operationService;

    public OperationsWindow(MainWindow mainWindow) {
        operationsWindow = new JDialog(mainWindow, "операции", Dialog.ModalityType.APPLICATION_MODAL);
        functionUI1 = new TabulatedFunctionUI();
        functionUI2 = new TabulatedFunctionUI();


        operationService = new TabulatedFunctionOperationService();


//        JLabel settings = new JLabel("Выберите тип создаваемой функции:");
        operationsWindow.setSize(300, 300);
        operationsWindow.setLocationRelativeTo(mainWindow);
        operationsWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Создание панели
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 3));
        operationsWindow.add(panel);


// Создание таблиц для первой, второй функции и результата

//        JTable firstFunctionTable = createFunctionTable();
//        JTable secondFunctionTable = createFunctionTable();
//        JTable resultTable = createFunctionTable();


        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("X1");
        model1.addColumn("Y1");
        model1.addColumn("X2");
        model1.addColumn("Y2");
//        firstFunctionTable.setModel(model1);
        table = new JTable(model1);

//        secondFunctionTable = new JTable();
//        DefaultTableModel model2 = new DefaultTableModel();
//        model2.addColumn("X");
//        model2.addColumn("Y");
//        secondFunctionTable.setModel(model2);
//
//        resultTable = new JTable();
//        DefaultTableModel model3 = new DefaultTableModel();
//        model3.addColumn("X");
//        model3.addColumn("Y");
//        resultTable.setModel(model3);
// Установка таблиц в панель
        panel.add(new JScrollPane(table));
//        panel.add(new JScrollPane(secondFunctionTable));
//        panel.add(new JScrollPane(resultTable));


// Создание сервиса операций над функциями
//1функ
        JButton create1ButtonArr = new JButton("Create First Function with arrays");
        JButton create1ButtonMath = new JButton("Create First Function with mathfunc");
        JButton load1Button = new JButton("Load First Function");
        JButton save1Button = new JButton("Save First Function");
//2функ
        JButton create2ButtonArr = new JButton("Create Second Function with arrays");
        JButton create2ButtonMath = new JButton("Create Second Function with mathfunc");
        JButton load2Button = new JButton("Load Second Function");
        JButton save2Button = new JButton("Save Second Function");
//операции
        JButton addButton = new JButton("Add");
        JButton subtractButton = new JButton("Subtract");
        JButton multiplyButton = new JButton("Multiply");
        JButton divideButton = new JButton("Divide");

//        //рез
//        JButton saveResultButton = new JButton("Save Result");
//        JButton addResButton = new JButton("Add res");
//        JButton subtractResButton = new JButton("Subtract res");
//        JButton multiplyResButton = new JButton("Multiply res ");
//        JButton divideResButton = new JButton("Divide res");

        panel.add(create1ButtonArr);
        panel.add(create1ButtonMath);
        panel.add(load1Button);
        panel.add(save1Button);
        panel.add(create2ButtonArr);
        panel.add(create2ButtonMath);
        panel.add(load2Button);
        panel.add(save2Button);
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);
//        panel.add(saveResultButton);
//        panel.add(addResButton);
//        panel.add(subtractResButton);
//        panel.add(multiplyResButton);
//        panel.add(divideResButton);


        //1я функ





        operationsWindow.setVisible(true);
    }

    private void saveFunctionInFile(JTable Table) {

        // Создание диалогового окна сохранения
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Настройка фильтра расширений файлов
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files (*.ser)", "ser");
        fileChooser.setFileFilter(filter);
        // Отображение диалогового окна сохранения
        int result = fileChooser.showSaveDialog(operationsWindow);

        if (result == JFileChooser.APPROVE_OPTION) {                    // Получение выбранного файла
            java.io.File file = fileChooser.getSelectedFile();
            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                FunctionsIO.serialize(outputStream, (TabulatedFunction) Table);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


    private void loadFunctionFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files (*.ser)", "ser");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(operationsWindow);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                TabulatedFunction tabulatedFunction = (TabulatedFunction) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                System.out.println(tabulatedFunction);
                JOptionPane.showMessageDialog(operationsWindow, "Function loaded successfully!");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(operationsWindow, "An error occurred while loading the function.");
            }
        }
    }





//    private void loadFunction(DefaultTableModel tableModel) {
//        JFileChooser fileChooser = new JFileChooser();
//        int choice = fileChooser.showOpenDialog(operationsWindow);
//
//        if (choice == JFileChooser.APPROVE_OPTION) {
//            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
//
//            try {
//                // Загрузка функции из файла
//                TabulatedFunction function = TabulatedFunctionLoader.loadTabulatedFunction(filePath);
//                updateTableModel(tableModel, function);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(operationsWindow,
//                        "Error loading function from file.",
//                        "Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }




//    public void updateTable(double[] xValues, double[] yValues) {
//        // Очистка таблицы
//        tableModel.setRowCount(0);
//
//        // Заполнение таблицы новыми значениями
//        for (int i = 0; i < xValues.length; i++) {
//            Object[] rowData = {xValues[i], yValues[i]};
//            tableModel.addRow(rowData);
//        }
//    }
//


//        private void createFunctionFromModel(DefaultTableModel model, TabulatedFunction Function)
//        {
//            int rowCount = model.getRowCount();
//            double[] xValues = new double[rowCount];
//            double[] yValues = new double[rowCount];
//            for (int i = 0; i < rowCount; i++) {
//                xValues[i] = Double.parseDouble(model.getValueAt(i, 0).toString());
//                yValues[i] = Double.parseDouble(model.getValueAt(i, 1).toString());
//            }
//            tabulatedFunctionUI = new TabulatedFunctionUI(xValues, yValues);
//            SwingUtilities.invokeLater(() -> Function = tabulatedFunctionUI.getTabulatedFunction());
//            System.out.println(Function);
//        }
//


}