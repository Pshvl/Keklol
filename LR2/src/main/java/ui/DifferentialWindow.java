package ui;

import javax.swing.*;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import io.FunctionsIO;
import operations.DifferentialOperator;
import operations.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.*;
import java.io.*;

import static ui.ExceptionMessage.showError;

public class DifferentialWindow extends JDialog {
    private JLabel resultLabel;
    private TabulatedDifferentialOperator tabulatedDifferentialOperator;
    double[] xValues;
    double[] yValues;

    public JTable tableFunc;
    public JTable tableResFunc;
    private TabulatedDifferentialOperator functionDifferentialOperator = new TabulatedDifferentialOperator();
    private TabulatedFunction tabulatedFunc;
    private TabulatedFunction tabulatedResFunc;

    public DifferentialWindow(MainWindow mainWindow, TabulatedFunctionFactory factory) {
        super(mainWindow, "Дифференциал", Dialog.ModalityType.APPLICATION_MODAL);
        setSize(1000, 600);
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 0, 0));

        JPanel funcPanel = new JPanel();
        JPanel resFuncPanel = new JPanel();

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        DefaultTableModel tableResModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);

        tableFunc = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column == 1);
            }
        };
        JScrollPane scrollPane1 = new JScrollPane(tableFunc);

        tableResFunc = new JTable(tableResModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane3 = new JScrollPane(tableResFunc);

        funcPanel.add(new JLabel("Функция:"), BorderLayout.NORTH);
        funcPanel.add(scrollPane1);

        resFuncPanel.add(new JLabel("Результат дифференцирования:"), BorderLayout.NORTH);
        resFuncPanel.add(scrollPane3);

        JButton create1ButtonArr = new JButton("Создать функцию (массивы)");
        create1ButtonArr.addActionListener(e -> {
            TabulatedFunctionUI tabulatedFunctionUI = new TabulatedFunctionUI(this, factory);

            tableModel.setRowCount(0);
            for (int i = 0; i < tabulatedFunc.getCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableModel.addRow(rowData);
            }
            setTabulatedFunc();
        });
        JButton create1ButtonMath = new JButton("Создать функцию (функция)");
        create1ButtonMath.addActionListener(e -> {
            CreateTabulatedFunctionSource tabulatedFunctionSourceWindow = new CreateTabulatedFunctionSource(this, factory);

            tableModel.setRowCount(0);
            for (int i = 0; i < tabulatedFunc.getCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableModel.addRow(rowData);
            }
            setTabulatedFunc();
        });
        JButton load1Button = new JButton("Загрузить");
        load1Button.addActionListener(e -> {
            loadFunctionFromFile();

            tableModel.setRowCount(0);
            for (int i = 0; i < tabulatedFunc.getCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableModel.addRow(rowData);
            }
            setTabulatedFunc();
        });
        JButton save1Button = new JButton("Сохранить");
        save1Button.addActionListener(e -> {
            for (int i = 0; i < tabulatedFunc.getCount(); i++) {
                try {
                    tabulatedFunc.setY(i, Double.parseDouble(tableModel.getValueAt(i, 1).toString()));
                } catch (NumberFormatException ex) {
                    showError("Неверный Ввод. Значения могут быть только ЧИСЛАМИ С ПЛАВАЮЩЕЙ ТОЧКОЙ.");
                    break;
                }
            }

            saveFunctionInFile(tabulatedFunc);
        });
        JPanel gridButton1Panel = new JPanel(new GridLayout(2, 2, 5, 5));
        gridButton1Panel.add(create1ButtonArr);
        gridButton1Panel.add(create1ButtonMath);
        gridButton1Panel.add(load1Button);
        gridButton1Panel.add(save1Button);


        JButton difButton = new JButton("Продифференцировать");
        difButton.addActionListener(e -> {
            try {
                for (int i = 0; i < tabulatedFunc.getCount(); i++) {
                    try {
                        tabulatedFunc.setY(i, Double.parseDouble(tableModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Неверный Ввод. Значения могут быть только ЧИСЛАМИ С ПЛАВАЮЩЕЙ ТОЧКОЙ.");
                        break;
                    }
                }

                tabulatedFunc = functionDifferentialOperator.derive(tabulatedFunc);
                tableResModel.setRowCount(0);
                for(int i = 0; i < tabulatedFunc.getCount(); i++) {

                    Object[] rowData = new Object[2];
                    rowData[0] = tabulatedFunc.getX(i);
                    rowData[1] = tabulatedFunc.getY(i);
                    tableResModel.addRow(rowData);
                }
                setTabulatedResFunc();
            } catch (RuntimeException ex) {
                showError("Количество точек у первой и второй функции должно быть одинаково И > 0");
            }
        });

        JButton save3Button = new JButton("Сохранить");
        save3Button.addActionListener(e -> {
            saveFunctionInFile(tabulatedResFunc);
        });

        JPanel gridButton3Panel = new JPanel(new GridLayout(0, 2, 5, 5) );
        gridButton3Panel.add(difButton);
        gridButton3Panel.add(save3Button);

        funcPanel.add(gridButton1Panel,BorderLayout.SOUTH);
        resFuncPanel.add(gridButton3Panel,BorderLayout.SOUTH);

        gridPanel.add(funcPanel);
        gridPanel.add(resFuncPanel);
        add(gridPanel);


// Создание панели
        /*JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);


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

        setVisible(true);

        JButton createButton = new JButton("Ñîçäàòü");*/
        setVisible(true);
    }

    public void setTabulatedFuncA(TabulatedFunction func){
        this.tabulatedFunc = func;
    }
    public void setTabulatedFunc(){
        this.tabulatedFunc = this.tabulatedFunc;
    }
    public void setTabulatedResFunc(){
        this.tabulatedResFunc = this.tabulatedFunc;
    }

/*        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TabulatedFunctionUI(xValues, yValues);
                initialFunction = (TabulatedFunction) TabulatedFunctionDatabase.database;

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
        });*/


/*        JButton differentiateButton = new JButton("compute");
        panel.add(differentiateButton);
        differentiateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                differentiatedFunction = tabulatedDifferentialOperator.deriveSynchronously((TabulatedFunction) initialFunction);
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
        });*/

    private void saveFunctionInFile(TabulatedFunction func) {
        if (func == null) {
            showError("Пожалуйста создайте функцию перед тем, как ее сохранять");
        } else {
            // Создание диалогового окна сохранения
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // Настройка фильтра расширений файлов
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files (*.ser)", "ser");
            fileChooser.setFileFilter(filter);
            // Отображение диалогового окна сохранения
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {                    // Получение выбранного файла
                java.io.File file = fileChooser.getSelectedFile();
                try {
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                    FunctionsIO.serialize(outputStream, func);
                } catch (IOException ex) {
                    showError("Произошла ошибка при записи функции.");
                }
            }
        }

    }


    private void loadFunctionFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files (*.ser)", "ser");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            try {
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                TabulatedFunction tabulatedFunction = FunctionsIO.deserialize(inputStream);
                setTabulatedFuncA(tabulatedFunction);
                System.out.println(tabulatedFunction);
                JOptionPane.showMessageDialog(this, "Функция была успешно загружена!");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Произошла ошибка при загрузке функции.");
            }
        }
    }
}

