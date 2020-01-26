package by.andd3dfx.pravtor.util;

import by.andd3dfx.pravtor.model.BatchSearchResult;
import by.andd3dfx.pravtor.model.SearchCriteria;
import by.andd3dfx.pravtor.model.TorrentData;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class FileUtil {

    private final String[] HEADER_LABELS = {"Название", "Seeds", "Peers", "Скачано", "Размер", "Ссылка"};

    /**
     * Load list of search criteria items
     *
     * @param fileName name of params file
     * @return list of search criteria items
     */
    public List<SearchCriteria> loadSearchCriteria(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName)).stream()
            .map(line -> {
                final String[] items = line.split("\t");
                return new SearchCriteria(items[0], items[1]);
            }).collect(Collectors.toList());
    }

    /**
     * Write set of search items into multi sheet excel file
     *
     * @param fileName name of excel file
     * @param searchItems list of items to save, where each represents one sheet in result excel file
     */
    public void writeIntoExcel(String fileName, List<BatchSearchResult> searchItems) throws IOException {
        Workbook book = new HSSFWorkbook();

        searchItems.forEach(searchItem -> {
            Sheet sheet = book.createSheet(searchItem.getTopic());

            populateHeaderLabels(sheet);
            populateContent(sheet, searchItem);
            setColumnsWidth(sheet);
        });

        book.write(new FileOutputStream(fileName));
        book.close();
    }

    private void populateHeaderLabels(Sheet sheet) {
        Row header = sheet.createRow(0);
        int header_column_number = 0;
        for (String label : HEADER_LABELS) {
            header.createCell(header_column_number++).setCellValue(label);
        }
    }

    private void populateContent(Sheet sheet, BatchSearchResult searchItem) {
        int rowsCount = 1;
        for (TorrentData dataItem : searchItem.getDataItems()) {
            int column_number = 0;
            Row row = sheet.createRow(rowsCount);
            row.createCell(column_number++).setCellValue(dataItem.getLabel());
            populateCellWithInteger(row.createCell(column_number++), dataItem.getSeedsCount());
            populateCellWithInteger(row.createCell(column_number++), dataItem.getPeersCount());
            populateCellWithInteger(row.createCell(column_number++), dataItem.getDownloadedCount());
            row.createCell(column_number++).setCellValue(dataItem.getSize());
            row.createCell(column_number++).setCellValue(dataItem.getLinkUrl());

            rowsCount++;
        }
    }

    private void setColumnsWidth(Sheet sheet) {
        sheet.setColumnWidth(0, 95 * 256);
        for (int i = 1; i <= 5; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void populateCellWithInteger(Cell cell, Integer intValue) {
        if (intValue == null) {
            return;
        }
        cell.setCellValue(intValue);
    }
}
