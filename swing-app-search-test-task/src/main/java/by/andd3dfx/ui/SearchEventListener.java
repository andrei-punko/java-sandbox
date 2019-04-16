package by.andd3dfx.ui;

import by.andd3dfx.search.PostSearchActionRunner;
import by.andd3dfx.search.SearchResultItem;
import by.andd3dfx.search.SearchTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchEventListener implements ActionListener, PostSearchActionRunner {

    private static final int RESULTS_PER_PAGE = 10;

    private JTextField searchStringTextField;
    private JTable searchResultTable;

    public SearchEventListener(JTextField searchStringTextField, JTable searchResultTable) {
        this.searchStringTextField = searchStringTextField;
        this.searchResultTable = searchResultTable;
    }

    public void actionPerformed(ActionEvent e) {
        String searchText = searchStringTextField.getText();

        SearchTask searchTask = new SearchTask(searchText, RESULTS_PER_PAGE, this);
        Thread thread = new Thread(searchTask);
        thread.start();
    }

    void updateTable(List<SearchResultItem> searchResultItems) {
        DefaultTableModel tableModel = (DefaultTableModel) searchResultTable.getModel();
        tableModel.setRowCount(0);

        Vector<Vector> rows = new Vector<Vector>();
        for (SearchResultItem searchResultItem : searchResultItems) {
            Vector<String> row = new Vector<String>();
            row.add(searchResultItem.getUrl());
            rows.add(row);
        }

        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Search results");
        tableModel.setDataVector(rows, columnNames);
    }

    @Override
    public void searchPerformed(List<SearchResultItem> searchResultItems) {
        updateTable(searchResultItems);
    }
}
