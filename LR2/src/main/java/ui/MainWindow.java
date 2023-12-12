package ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;
import javax.swing.UIManager;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class MainWindow extends JFrame {

    private TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    public MainWindow mainWindow = this;
    //    public FunctionDatabase database = new FunctionDatabase();
    public TabulatedFunctionFactory functionFactory = new ArrayTabulatedFunctionFactory();

    //    public FunctionList functionList;
    public MainWindow() {

        setTitle("Главное окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
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

        JPanel gridPanel = new JPanel(new GridLayout(3, 0, 40, 0) );

        /*// Создание кнопки для открытия других окон
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
        });*/



        JButton settings = new JButton("Настройки");
        JPanel settingsPanel = new JPanel();
        settingsPanel.add(settings);
        settings.addActionListener(e -> {
            // Создание нового окна
            new SettingsWindow(mainWindow, factory);
        });

        JButton operations = new JButton("Операции");
        JPanel operationsPanel = new JPanel();
        operationsPanel.add(operations);
        operations.addActionListener(e -> {
            // Создание нового окна
           OperationsWindow opers = new OperationsWindow(mainWindow, factory);
        });

        JButton differential = new JButton("Дифференциал");
        JPanel differentialPanel = new JPanel();
        differentialPanel.add(differential);
      /*  differential.addActionListener(e -> {
            // Создание нового окна
            new DifferentialWindow(mainWindow);
        });*/

        gridPanel.add(operationsPanel);
        gridPanel.add(differentialPanel);
        gridPanel.add(settingsPanel);
        add(gridPanel, BorderLayout.CENTER);
    }

    public void setFactory(TabulatedFunctionFactory fact){
        this.factory = fact;
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

