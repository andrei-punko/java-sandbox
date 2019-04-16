package by.andd3dfx.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

    private static final String WINDOW_TITLE = "Belprime test task";
    private static final String SEARCH_BUTTON_LABEL = "Search";
    private static final int SEARCH_FIELD_COLUMNS_COUNT = 50;

    public MainFrame() {
        super(WINDOW_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable searchResultTable = new JTable(new DefaultTableModel());
        JTextField searchStringTextField = new JTextField(SEARCH_FIELD_COLUMNS_COUNT);
        JButton searchButton = new JButton(SEARCH_BUTTON_LABEL);

        SearchEventListener searchEventListener = new SearchEventListener(searchStringTextField, searchResultTable);
        searchStringTextField.addActionListener(searchEventListener);
        searchButton.addActionListener(searchEventListener);

        JPanel upperPanel = new JPanel();
        upperPanel.add(searchStringTextField);
        upperPanel.add(searchButton);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(upperPanel, BorderLayout.NORTH);
        container.add(new JScrollPane(searchResultTable), BorderLayout.CENTER);

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getWidth()) / 2, (dim.height - getHeight()) / 2);
        setVisible(true);
        setResizable(false);
    }
}
