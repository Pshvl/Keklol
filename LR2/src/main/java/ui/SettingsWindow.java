package ui;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JDialog {
    public SettingsWindow(MainWindow mainWindow) {

/*
        JDialog settingsWindow = new JDialog(mainWindow, "Настройки", Dialog.ModalityType.APPLICATION_MODAL);
//        JLabel settingsWindow = new JLabel("Выберите тип создаваемой функции:");
        settingsWindow.setLocationRelativeTo(mainWindow);
   settingsWindow.setVisible(true);
    settingsWindow.setSize(600, 600);
        settingsWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

// Установка родительского окна в качестве владельца

// Открытие окна настроек в модальном режиме


//        settingsWindow.setModal(true);
//       settingsWindow.setLayout(null);
//        settingsWindow.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
//








        // Создание выпадающего списка с вариантами фабрик
        JComboBox<String> factoryComboBox = new JComboBox<>(new String[]{"Array", "Linked List"});
        settingsWindow.getContentPane().add(factoryComboBox);

        JPanel panel = new JPanel();


        // Создаем радиокнопки для выбора фабрики
        JRadioButton arrayRadioButton = new JRadioButton("Массив");
        JRadioButton linkedListRadioButton = new JRadioButton("Связный список");

        // Группируем радиокнопки в ButtonGroup
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(arrayRadioButton);
        buttonGroup.add(linkedListRadioButton);

        // Обработчик события нажатия на кнопку сохранения

        saveButton.addActionListener(new ActionListener() {
            @Override // Получение выбранной фабрики
            public void actionPerformed(ActionEvent e) {

                if (factoryComboBox.getSelectedItem().equals("Array")) {
                    mainWindow.functionFactory = new ArrayTabulatedFunctionFactory();
                } else {
                    mainWindow.functionFactory = new LinkedListTabulatedFunctionFactory();
                }// Закрытие окна настроек
                settingsWindow.dispose();
            }
        });


        setContentPane(panel);
        mainWindow.add(panel);
        panel.add(new JLabel("Select function factory:"));
        panel.add(factoryComboBox);
        panel.add(saveButton);
        add(factoryComboBox, BorderLayout.NORTH);*/


        JDialog settingsWindow = new JDialog(mainWindow, "Настройки", Dialog.ModalityType.APPLICATION_MODAL);
        JLabel settings = new JLabel("Выберите тип создаваемой функции:");
        settingsWindow.setSize(300, 300);
        settingsWindow.setLocationRelativeTo(mainWindow);
        settingsWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Создание кнопки для сохранения выбранной фабрики
        JButton saveButton = new JButton("Сохранить");
        saveButton.setBounds(100, 100, 100, 30);
        settingsWindow.add(saveButton, BorderLayout.SOUTH);
        settingsWindow.add(settings, BorderLayout.NORTH);


        // Создаем радиокнопки для выбора фабрики
        JRadioButton arrayRadioButton = new JRadioButton("Массив");
        JRadioButton linkedListRadioButton = new JRadioButton("Связный список");

        // Группируем радиокнопки в ButtonGroup
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(arrayRadioButton);
        buttonGroup.add(linkedListRadioButton);
        settingsWindow.add(arrayRadioButton,BorderLayout.EAST);
        settingsWindow.add(linkedListRadioButton,BorderLayout.WEST);
        arrayRadioButton.setSelected(true);


// Обработчик события нажатия на кнопку сохранения

        saveButton.addActionListener(new ActionListener() {
            @Override // Получение выбранной фабрики
            public void actionPerformed(ActionEvent e) {

                if (arrayRadioButton.isSelected()) {
                    mainWindow.functionFactory = new ArrayTabulatedFunctionFactory();
                } else {
                    mainWindow.functionFactory = new LinkedListTabulatedFunctionFactory();
                }// Закрытие окна настроек
                settingsWindow.dispose();
            }
        });















        settingsWindow.setVisible(true);


    }


}




