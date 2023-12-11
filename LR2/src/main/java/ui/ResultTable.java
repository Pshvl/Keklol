package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import functions.TabulatedFunction;

public class ResultTable extends JDialog {
    private TabulatedFunction resultFunction;
    private JTable table;
    private DefaultTableModel tableModel;
    public ResultTable(TabulatedFunction resultFunction) {
        setTitle("Result Function");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Инициализация компонентов
        tableModel = new DefaultTableModel();
        tableModel.addColumn("X");
        tableModel.addColumn("Y");
        table = new JTable(tableModel);
        JButton saveResultButton = new JButton("Save Result");
        JButton addResButton = new JButton("Add res");
        JButton subtractResButton = new JButton("Subtract res");
        JButton multiplyResButton = new JButton("Multiply res ");
        JButton divideResButton = new JButton("Divide res");

        // Заполнение таблицы результатами
        for (int i = 0; i < resultFunction.getCount(); i++) {
            Object[] rowData = {resultFunction.getX(i), resultFunction.getY(i)};
            tableModel.addRow(rowData);
        }

        // Расположение компонентов

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(saveResultButton, BorderLayout.CENTER);
        add(addResButton, BorderLayout.CENTER);
        add(subtractResButton, BorderLayout.CENTER);
        add(multiplyResButton, BorderLayout.CENTER);
        add(divideResButton, BorderLayout.CENTER);
    }}











