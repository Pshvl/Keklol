package ui;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JDialog {
    private final TabulatedFunctionFactory factoryArray = new ArrayTabulatedFunctionFactory();
    private final TabulatedFunctionFactory factoryLinkList = new LinkedListTabulatedFunctionFactory();
    public SettingsWindow(MainWindow mainWindow, TabulatedFunctionFactory factory) {

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

        if (factory instanceof ArrayTabulatedFunctionFactory){
            arrayRadioButton.setSelected(true);
        }
        else {
            linkedListRadioButton.setSelected(true);
        }

// Обработчик события нажатия на кнопку сохранения

        saveButton.addActionListener(new ActionListener() {
            @Override // Получение выбранной фабрики
            public void actionPerformed(ActionEvent e) {

                if (arrayRadioButton.isSelected()) {
                   mainWindow.setFactory(factoryArray);
                } else {
                    mainWindow.setFactory(factoryLinkList);
                }// Закрытие окна настроек
                settingsWindow.dispose();
            }
        });















        settingsWindow.setVisible(true);


    }


}




