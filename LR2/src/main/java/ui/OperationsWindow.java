package ui;

import operations.TabulatedFunctionOperationService;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import io.FunctionsIO;

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


public class OperationsWindow extends JDialog {
    public OperationsWindow(MainWindow mainWindow) {
        JDialog operationsWindow = new JDialog(mainWindow, "операции", Dialog.ModalityType.APPLICATION_MODAL);
//        JLabel settings = new JLabel("Выберите тип создаваемой функции:");
        operationsWindow.setSize(300, 300);
        operationsWindow.setLocationRelativeTo(mainWindow);
        operationsWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);





































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
//                        TabulatedFunction tabulatedFunction = (TabulatedFunction) objectInputStream.readObject();
                        // Закрытие потоков  objectInputStream.close();
                        fileInputStream.close();
                        // Вывод десериализованной функции
//                        System.out.println(tabulatedFunction);
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
}
