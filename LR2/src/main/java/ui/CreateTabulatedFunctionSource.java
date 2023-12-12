package ui;

import functions.*;
import functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static ui.ExceptionMessage.showError;

public class CreateTabulatedFunctionSource extends JDialog {
    private TabulatedFunctionFactory factory;

    private JTextField pointsTextField;
    private JTextField interval_1TextField;
    private JTextField interval_2TextField;
    private JComboBox funcBox;
    private HashMap<String, Object> functionsMap;

    public CreateTabulatedFunctionSource(OperationsWindow operationsWindow, TabulatedFunctionFactory factory) {
        super(operationsWindow, "Создание функции на основе другой функции", Dialog.ModalityType.APPLICATION_MODAL);
        setSize(400, 300);
        setLocationRelativeTo(operationsWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        String[] functions = {
                "Единичная функция",
                "Квадратичная функция",
                "Косинус функция",
                "Нулевая функция",
                "Тангенс функция",
                "Тождественная функция"
        };
        funcBox = new JComboBox(functions);

        JPanel inputPanel = new JPanel();
        pointsTextField = new JTextField(10);
        interval_1TextField = new JTextField(10);
        interval_2TextField = new JTextField(10);

        JPanel funcPanel = new JPanel();
        funcPanel.add(new JLabel("Вид функции:"));
        funcPanel.add(funcBox);
        funcPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));


        JPanel pointsPanel = new JPanel();
        pointsPanel.add(new JLabel("Кол-во точек разбиения:"));
        pointsPanel.add(pointsTextField);
        pointsPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JPanel interval_1Panel = new JPanel();
        interval_1Panel.add(new JLabel("Начало разбиения:"));
        interval_1Panel.add(interval_1TextField);
        interval_1Panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JPanel interval_2Panel = new JPanel();
        interval_2Panel.add(new JLabel("Конец разбиения:"));
        interval_2Panel.add(interval_2TextField);

        JPanel gridPanel = new JPanel(new GridLayout(4, 0, 30, 0) );
        gridPanel.add(funcPanel);
        gridPanel.add(pointsPanel);
        gridPanel.add(interval_1Panel);
        gridPanel.add(interval_2Panel);

        JButton createFunc = new JButton("Создать функцию");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createFunc);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 150));

        functionsMap = new HashMap<>();
        functionsMap.put("Единичная функция", new UnitFunction());
        functionsMap.put("Квадратичная функция", new SqrFunction());
        functionsMap.put("Косинус функция", new CosFunction());
        functionsMap.put("Нулевая функция", new ZeroFunction());
        functionsMap.put("Тангенс функция", new VarFunction());
        functionsMap.put("Тождественная функция", new IdentityFunction());

        createFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTabulatedFunction(operationsWindow, factory);
            }
        });

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public CreateTabulatedFunctionSource(DifferentialWindow operationsWindow, TabulatedFunctionFactory factory) {
        super(operationsWindow, "Создание функции на основе другой функции", Dialog.ModalityType.APPLICATION_MODAL);
        setSize(400, 300);
        setLocationRelativeTo(operationsWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        String[] functions = {
                "Единичная функция",
                "Квадратичная функция",
                "Косинус функция",
                "Нулевая функция",
                "Тангенс функция",
                "Тождественная функция"
        };
        funcBox = new JComboBox(functions);

        JPanel inputPanel = new JPanel();
        pointsTextField = new JTextField(10);
        interval_1TextField = new JTextField(10);
        interval_2TextField = new JTextField(10);

        JPanel funcPanel = new JPanel();
        funcPanel.add(new JLabel("Вид функции:"));
        funcPanel.add(funcBox);
        funcPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));


        JPanel pointsPanel = new JPanel();
        pointsPanel.add(new JLabel("Кол-во точек разбиения:"));
        pointsPanel.add(pointsTextField);
        pointsPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JPanel interval_1Panel = new JPanel();
        interval_1Panel.add(new JLabel("Начало разбиения:"));
        interval_1Panel.add(interval_1TextField);
        interval_1Panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JPanel interval_2Panel = new JPanel();
        interval_2Panel.add(new JLabel("Конец разбиения:"));
        interval_2Panel.add(interval_2TextField);

        JPanel gridPanel = new JPanel(new GridLayout(4, 0, 30, 0) );
        gridPanel.add(funcPanel);
        gridPanel.add(pointsPanel);
        gridPanel.add(interval_1Panel);
        gridPanel.add(interval_2Panel);

        JButton createFunc = new JButton("Создать функцию");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createFunc);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 150));

        functionsMap = new HashMap<>();
        functionsMap.put("Единичная функция", new UnitFunction());
        functionsMap.put("Квадратичная функция", new SqrFunction());
        functionsMap.put("Косинус функция", new CosFunction());
        functionsMap.put("Нулевая функция", new ZeroFunction());
        functionsMap.put("Тангенс функция", new VarFunction());
        functionsMap.put("Тождественная функция", new IdentityFunction());

        createFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTabulatedFunction(operationsWindow, factory);
            }
        });

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void createTabulatedFunction(OperationsWindow operationsWindow, TabulatedFunctionFactory newFactory) {
        this.factory = newFactory;

        int numberOfPoints = -3;
        double xFrom = 0;
        double xTo = 0;

        try {
            numberOfPoints = Integer.parseInt(pointsTextField.getText());
            if (numberOfPoints <= 0) {
                showError("Неверный Ввод. Количество точек должно быть ЦЕЛЫМ и > 0.");
                return;
            }
            xFrom = Double.parseDouble(interval_1TextField.getText());
            xTo = Double.parseDouble(interval_2TextField.getText());
            String mathFunc = (String) funcBox.getSelectedItem();

            TabulatedFunction func = factory.create((MathFunction) functionsMap.get(mathFunc), xFrom, xTo, numberOfPoints);
            operationsWindow.setTabulatedFunc(func);
            System.out.println("Tabulated Function created: " + func);
            dispose();
        } catch (NumberFormatException ex) {
            showError("Неверный Ввод. Пожалуйста введите ЧИСЛА.");
        }
    }

        private void createTabulatedFunction(DifferentialWindow operationsWindow, TabulatedFunctionFactory newFactory) {
            this.factory = newFactory;

            int numberOfPoints = -3;
            double xFrom = 0;
            double xTo = 0;

            try {
                numberOfPoints = Integer.parseInt(pointsTextField.getText());
                if (numberOfPoints <= 0) {
                    showError("Неверный Ввод. Количество точек должно быть ЦЕЛЫМ и > 0.");
                    return;
                }
                xFrom = Double.parseDouble(interval_1TextField.getText());
                xTo = Double.parseDouble(interval_2TextField.getText());
                String mathFunc = (String) funcBox.getSelectedItem();

                TabulatedFunction func = factory.create((MathFunction) functionsMap.get(mathFunc), xFrom, xTo, numberOfPoints);
                operationsWindow.setTabulatedFuncA(func);
                System.out.println("Tabulated Function created: " + func);
                dispose();
            } catch (NumberFormatException ex) {
                showError("Неверный Ввод. Пожалуйста введите ЧИСЛА.");
            }
        }



    }
