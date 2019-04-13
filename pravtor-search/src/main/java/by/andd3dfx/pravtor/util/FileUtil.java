package by.andd3dfx.pravtor.util;

import by.andd3dfx.pravtor.model.BatchSearchResult;
import by.andd3dfx.pravtor.model.SearchCriteria;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class FileUtil {

    /**
     * Loads list of search criteria items
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

            AtomicInteger rowsCount = new AtomicInteger();
            searchItem.getDataItems().forEach(dataItem -> {

                Row row = sheet.createRow(rowsCount.get());
                row.createCell(0).setCellValue(dataItem.getLabel());
                populateCellWithInteger(row.createCell(1), dataItem.getSeedsCount());
                populateCellWithInteger(row.createCell(2), dataItem.getPeersCount());
                populateCellWithInteger(row.createCell(3), dataItem.getDownloadedCount());
                row.createCell(4).setCellValue(dataItem.getSize());
                row.createCell(5).setCellValue(dataItem.getLinkUrl());

                rowsCount.getAndIncrement();
            });

            sheet.setColumnWidth(0, 95*256);
            for (int i = 1; i <= 5; i++) {
                sheet.autoSizeColumn(i);
            }
        });

        book.write(new FileOutputStream(fileName));
        book.close();
    }

    private void populateCellWithInteger(Cell cell, Integer intValue) {
        if (intValue == null) {
            return;
        }
        cell.setCellValue(intValue);
    }
}
