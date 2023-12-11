package ui;
import functions.factory.TabulatedFunctionFactory;
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
    private TabulatedFunctionFactory factory;
    public JTable tableFstFunc;
    public JTable tableSecFunc;
    public JTable tableResFunc;
    private TabulatedFunctionUI functionUI1;
    private TabulatedFunctionUI functionUI2;
//    public JTable secondFunctionTable;
//    public JTable resultTable;
    private TabulatedFunctionOperationService operationService;

    public OperationsWindow(MainWindow mainWindow, TabulatedFunctionFactory factory) {
        this.factory = factory;

        operationsWindow = new JDialog(mainWindow, "Операции", Dialog.ModalityType.APPLICATION_MODAL);
        operationsWindow.setSize(1500, 600);
        operationsWindow.setLocationRelativeTo(mainWindow);
        operationsWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //functionUI1 = new TabulatedFunctionUI();
        //functionUI2 = new TabulatedFunctionUI();


        //operationService = new TabulatedFunctionOperationService();

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 0, 0) );

        JPanel fstFuncPanel = new JPanel();
        JPanel secFuncPanel = new JPanel();
        JPanel resFuncPanel = new JPanel();

        DefaultTableModel tableFstModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        DefaultTableModel tableSecModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        DefaultTableModel tableResModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);

        tableFstFunc = new JTable(tableFstModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return (column==1);
            }
        };
        JScrollPane scrollPane1 = new JScrollPane(tableFstFunc);

        tableSecFunc = new JTable(tableSecModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return (column==1);
            }
        };
        JScrollPane scrollPane2 = new JScrollPane(tableSecFunc);

        tableResFunc = new JTable(tableResModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        JScrollPane scrollPane3 = new JScrollPane(tableResFunc);

        fstFuncPanel.add(new JLabel("Первая функция:"), BorderLayout.NORTH);
        fstFuncPanel.add(scrollPane1);

        secFuncPanel.add(new JLabel("Вторая функция:"), BorderLayout.NORTH);
        secFuncPanel.add(scrollPane2);

        resFuncPanel.add(new JLabel("Результат:"), BorderLayout.NORTH);
        resFuncPanel.add(scrollPane3);


// Создание сервиса операций над функциями
//1функ
        JButton create1ButtonArr = new JButton("Создать функцию (массивы)");
        JButton create1ButtonMath = new JButton("Создать функцию (функция)");
        JButton load1Button = new JButton("Загрузить");
        JButton save1Button = new JButton("Сохранить");



        JPanel gridButton1Panel = new JPanel(new GridLayout(2, 2, 5, 5));
        gridButton1Panel.add(create1ButtonArr);
        gridButton1Panel.add(create1ButtonMath);
        gridButton1Panel.add(load1Button);
        gridButton1Panel.add(save1Button);
//2функ
        JButton create2ButtonArr = new JButton("Создать функцию (массивы)");
        JButton create2ButtonMath = new JButton("Создать функцию (функция)");
        JButton load2Button = new JButton("Загрузить");
        JButton save2Button = new JButton("Сохранить");

        JPanel gridButton2Panel = new JPanel(new GridLayout(2, 2, 5, 5) );
        gridButton2Panel.add(create2ButtonArr);
        gridButton2Panel.add(create2ButtonMath);
        gridButton2Panel.add(load2Button);
        gridButton2Panel.add(save2Button);
//операции
        JButton addButton = new JButton("Сложить");
        JButton subtractButton = new JButton("Вычесть");
        JButton multiplyButton = new JButton("Умножить");
        JButton divideButton = new JButton("Поделить");
        JButton save3Button = new JButton("Сохранить");

        JPanel gridButton3Panel = new JPanel(new GridLayout(2, 3, 5, 5) );
        gridButton3Panel.add(addButton);
        gridButton3Panel.add(subtractButton);
        gridButton3Panel.add(save3Button);
        gridButton3Panel.add(multiplyButton);
        gridButton3Panel.add(divideButton);

        fstFuncPanel.add(gridButton1Panel,BorderLayout.SOUTH);
        secFuncPanel.add(gridButton2Panel,BorderLayout.SOUTH);
        resFuncPanel.add(gridButton3Panel,BorderLayout.SOUTH);

        gridPanel.add(fstFuncPanel);
        gridPanel.add(secFuncPanel);
        gridPanel.add(resFuncPanel);
        operationsWindow.add(gridPanel);

        create1ButtonMath.addActionListener(e -> {
            new CreateTabulatedFunctionSource(operationsWindow, factory);
        });

        create1ButtonArr.addActionListener(e -> {
            new TabulatedFunctionUI(operationsWindow, factory);
        });

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