package ui;

import operations.TabulatedFunctionOperationService;
//import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
//import functions.factory.LinkedListTabulatedFunctionFactory;
//import io.FunctionsIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
//import java.util.Arrays;
import javax.swing.table.DefaultTableModel;


public class OperationsWindow extends JDialog {
    private JDialog operationsWindow;
    private DefaultTableModel firstFunctionTableModel;
    private DefaultTableModel secondFunctionTableModel;
    private DefaultTableModel resultTableModel;
    private TabulatedFunctionOperationService operationService;

    public OperationsWindow(MainWindow mainWindow) {
       operationsWindow = new JDialog(mainWindow, "операции", Dialog.ModalityType.APPLICATION_MODAL);
//        JLabel settings = new JLabel("Выберите тип создаваемой функции:");
        operationsWindow.setSize(300, 300);
        operationsWindow.setLocationRelativeTo(mainWindow);
        operationsWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Создание панели
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        operationsWindow.add(panel);

// Создание таблиц для первой, второй функции и результата
        JTable firstFunctionTable = createFunctionTable();
        JTable secondFunctionTable = createFunctionTable();
        JTable resultTable = createFunctionTable();

        // Установка таблиц в панель
        panel.add(new JScrollPane(firstFunctionTable));
        panel.add(new JScrollPane(secondFunctionTable));
        panel.add(new JScrollPane(resultTable));


        // Создание кнопок для работы с первой функцией
        JPanel firstFunctionButtonPanel = new JPanel();
        firstFunctionButtonPanel.setLayout(new GridLayout(3, 1));

        JButton createFirstFunctionButton = new JButton("Create");
        createFirstFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFunction(firstFunctionTableModel);
            }
        });
        firstFunctionButtonPanel.add(createFirstFunctionButton);

        JButton loadFirstFunctionButton = new JButton("Load");
        loadFirstFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFunction(firstFunctionTableModel);
            }
        });
        firstFunctionButtonPanel.add(loadFirstFunctionButton);

        JButton saveFirstFunctionButton = new JButton("Save");
        saveFirstFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFunction(firstFunctionTableModel);
            }
        });
        firstFunctionButtonPanel.add(saveFirstFunctionButton);

        panel.add(firstFunctionButtonPanel);

        // Создание кнопок для работы со второй функцией
        JPanel secondFunctionButtonPanel = new JPanel();
        secondFunctionButtonPanel.setLayout(new GridLayout(3, 1));

        JButton createSecondFunctionButton = new JButton("Create");
        createSecondFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFunction(secondFunctionTableModel);
            }
        });
        secondFunctionButtonPanel.add(createSecondFunctionButton);

        JButton loadSecondFunctionButton = new JButton("Load");
        loadSecondFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFunction(secondFunctionTableModel);
            }
        });
        secondFunctionButtonPanel.add(loadSecondFunctionButton);

        JButton saveSecondFunctionButton = new JButton("Save");
        saveSecondFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFunction(secondFunctionTableModel);
            }
        });
        secondFunctionButtonPanel.add(saveSecondFunctionButton);

        panel.add(secondFunctionButtonPanel);

        // Создание кнопок для операций над функциями
        JPanel operationButtonPanel = new JPanel();
        operationButtonPanel.setLayout(new GridLayout(4, 1));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(firstFunctionTableModel, secondFunctionTableModel, resultTableModel, TabulatedFunctionOperation.ADD);
            }
        });
        operationButtonPanel.add(addButton);

        JButton subtractButton = new JButton("Subtract");
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(firstFunctionTableModel, secondFunctionTableModel, resultTableModel, TabulatedFunctionOperation.SUBTRACT);
            }
        });
        operationButtonPanel.add(subtractButton);

        JButton multiplyButton = new JButton("Multiply");
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(firstFunctionTableModel, secondFunctionTableModel, resultTableModel, TabulatedFunctionOperation.MULTIPLY);
            }
        });
        operationButtonPanel.add(multiplyButton);

        JButton divideButton = new JButton("Divide");
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(firstFunctionTableModel, secondFunctionTableModel, resultTableModel, TabulatedFunctionOperation.DIVIDE);
            }
        });
        operationButtonPanel.add(divideButton);

        panel.add(operationButtonPanel);

        operationsWindow.setVisible(true);

        // Создание сервиса операций над функциями
        operationService = new TabulatedFunctionOperationService();
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(100, 100, 100, 30);
        operationsWindow.add(saveButton, BorderLayout.SOUTH);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    try {                        // Создание OutputStream для записи объектов в файл
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        // Запись сериализованного объекта в файл
//                    objectOutputStream.writeObject(tabulatedFunction);
                        // Закрытие потоков objectOutputStream.close();
                        fileOutputStream.close();
                        JOptionPane.showMessageDialog(operationsWindow, "Function saved successfully!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(operationsWindow, "An error occurred while saving the function.");
                    }
                }
            }
        });


        // Создание кнопки загрузки
        JButton loadButton = new JButton("Load");
        loadButton.setBounds(100, 100, 100, 30);
        operationsWindow.add(loadButton, BorderLayout.WEST);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создание диалогового окна выбора файла
                JFileChooser fileChooser = new JFileChooser();
                // Ограничение выбора только файлов
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                // Настройка фильтра расширений файлов
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files (*.ser)", "ser");
                fileChooser.setFileFilter(filter);
                // Отображение диалогового окна выбора файла
                int result = fileChooser.showOpenDialog(operationsWindow);
                if (result == JFileChooser.APPROVE_OPTION) {                    // Получение выбранного файла
                    java.io.File file = fileChooser.getSelectedFile();
                    try {                        // Создание InputStream для чтения объектов из файла
                        FileInputStream fileInputStream = new FileInputStream(file);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        // Десериализация объекта и запись в таблицу
//                       TabulatedFunction tabulatedFunction = (TabulatedFunction) objectInputStream.readObject();
                        // Закрытие потоков  objectInputStream.close();
                        fileInputStream.close();
                        // Вывод десериализованной функции
//                       System.out.println(tabulatedFunction);
                        JOptionPane.showMessageDialog(operationsWindow, "Function loaded successfully!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(operationsWindow, "An error occurred while loading the function.");
                    }
                                                     }
                                                 }
                                             });
        operationsWindow.setVisible(true);
    }

    private JTable createFunctionTable() {
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[]{"x", "y"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };

        JTable table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        return table;
    }

    private void createFunction(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();

        String[] options = {"From Array", "From Another Function"};
        int choice = JOptionPane.showOptionDialog(operationsWindow,
                "Choose function creation source:",
                "Create Function",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            String input = JOptionPane.showInputDialog(operationsWindow,
                    "Enter space-separated x and y values:",
                    "Create Function - From Array",
                    JOptionPane.INFORMATION_MESSAGE);
            String[] values = input.split(" ");

            if (values.length % 2 != 0) {
                JOptionPane.showMessageDialog(operationsWindow,
                        "Invalid input format. Please enter space-separated x and y values.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < values.length; i += 2) {
                String x = values[i];
                String y = values[i + 1];

                tableModel.addRow(new Object[]{x, y});
            }
        } else if (choice == 1) {
            // Получение выбранной ячейки из другой таблицы
            JTable targetTable = (JTable) JOptionPane.showInputDialog(operationsWindow,
                    "Choose a function from another table:",
                    "Create Function - From Another Function",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new JTable[]{firstFunctionTableModel, secondFunctionTableModel},
                    firstFunctionTableModel);

            if (targetTable != null) {
                int selectedRow = targetTable.getSelectedRow();

                if (selectedRow != -1) {
                    Object x = targetTable.getValueAt(selectedRow, 0);
                    Object y = targetTable.getValueAt(selectedRow, 1);

                    tableModel.addRow(new Object[]{x, y});
                }
            }
        }
    }

    private void loadFunction(DefaultTableModel tableModel) {
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showOpenDialog(operationsWindow);

        if (choice == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            try {
                // Загрузка функции из файла
                TabulatedFunction function = TabulatedFunctionLoader.loadTabulatedFunction(filePath);
                updateTableModel(tableModel, function);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(operationsWindow,
                        "Error loading function from file.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFunction(DefaultTableModel tableModel) {
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showSaveDialog(operationsWindow);

        if (choice == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            try {
                int rowCount = tableModel.getRowCount();
                int columnCount = tableModel.getColumnCount();

                double[] xValues = new double[rowCount];
                double[] yValues = new double[rowCount];

                for (int i = 0; i < rowCount; i++) {
                    xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                    yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
                }

                // Создание функции и сохранение ее в файл
                TabulatedFunction function = TabulatedFunctionFactory.create(xValues, yValues);
                TabulatedFunctionLoader.saveTabulatedFunction(function, filePath);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(operationsWindow,
                        "Error saving function to file.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }







    private void updateTableModel(DefaultTableModel tableModel, TabulatedFunction function) {
        int rowCount = tableModel.getRowCount();

        // Удаление всех строк из таблицы
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        // Добавление значений из функции в таблицу
        double[] xValues = function.getX();
        double[] yValues = function.getY();

        for (int i = 0; i < xValues.length; i++) {
            tableModel.addRow(new Object[]{xValues[i], yValues[i]});
        }
    }

    private void performOperation(DefaultTableModel firstFunctionTableModel, DefaultTableModel secondFunctionTableModel,
                                  DefaultTableModel resultTableModel, TabulatedFunctionOperation operation) {
        try {
            int firstRowCount = firstFunctionTableModel.getRowCount();
            int secondRowCount = secondFunctionTableModel.getRowCount();

            double[] firstXValues = new double[firstRowCount];
            double[] firstYValues = new double[firstRowCount];

            double[] secondXValues = new double[secondRowCount];
            double[] secondYValues = new double[secondRowCount];

            for (int i = 0; i < firstRowCount; i++) {
                firstXValues[i] = Double.parseDouble(firstFunctionTableModel.getValueAt(i, 0).toString());
                firstYValues[i] = Double.parseDouble(firstFunctionTableModel.getValueAt(i, 1).toString());
            }

            for (int i = 0; i < secondRowCount; i++) {
                secondXValues[i] = Double.parseDouble(secondFunctionTableModel.getValueAt(i, 0).toString());
                secondYValues[i] = Double.parseDouble(secondFunctionTableModel.getValueAt(i, 1).toString());
            }

            // Создание табулированных функций и выполнение операции
            TabulatedFunction firstFunction = TabulatedFunctionFactory.create(firstXValues, firstYValues);
            TabulatedFunction secondFunction = TabulatedFunctionFactory.create(secondXValues, secondYValues);
            TabulatedFunction resultFunction = operationService.performOperation(firstFunction, secondFunction, operation);

            // Обновление таблицы результата
            updateTableModel(resultTableModel, resultFunction);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(operationsWindow,
                    "Error performing function operation.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new OperationsWindow();
//            }
//        });
//    }
}