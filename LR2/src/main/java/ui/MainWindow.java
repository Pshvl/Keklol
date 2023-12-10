package ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import functions.ArrayTabulatedFunction;

public class MainWindow extends JFrame {

    public MainWindow mainWindow = this;
    //    public FunctionDatabase database = new FunctionDatabase();
    public TabulatedFunctionFactory functionFactory = new ArrayTabulatedFunctionFactory();

    //    public FunctionList functionList;
    public MainWindow() {

        setTitle("Главное окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Обработчик события закрытия главного окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Завершение выполнения программы при закрытии главного окна
                //super.windowClosing(e);
                System.exit(0);
            }
        });

        // Создание кнопки для открытия других окон
        JButton openWindowButton = new JButton("открыть новое окно");
        openWindowButton.setBounds(100, 50, 200, 50);

        getContentPane().add(openWindowButton, BorderLayout.CENTER);

        // Добавление обработчика события нажатия на кнопку
        openWindowButton.addActionListener(e -> {
            // Создание нового окна
            JFrame otherWindow = new JFrame("Other Window");
            otherWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            otherWindow.setSize(300, 200);
            otherWindow.setVisible(true);
        });



        JButton settings = new JButton("настройки");
        getContentPane().add(settings, BorderLayout.NORTH);
        settings.addActionListener(e -> {
            // Создание нового окна
            new SettingsWindow(mainWindow);
        });

        JButton operations = new JButton("операции");
        getContentPane().add(operations, BorderLayout.WEST);
        operations.addActionListener(e -> {
            // Создание нового окна
            new OperationsWindow(mainWindow);
        });

        JButton differential = new JButton("операции");
        getContentPane().add(differential, BorderLayout.WEST);
        differential.addActionListener(e -> {
            // Создание нового окна
            new DifferentialWindow(mainWindow);
        });
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
        SwingUtilities.invokeLater(() -> {

            new MainWindow().setVisible(true);

        });

    }
}

