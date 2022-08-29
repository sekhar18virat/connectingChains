package com.example.cc.helper;

import com.example.cc.entity.Inventory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"code", "name", "batch", "stock", "deal", "free", "mrp", "rate", "exp", "company", "supplier"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Inventory> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Inventory> inventoryItems = new ArrayList<Inventory>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Inventory tutorial = new Inventory(
                        csvRecord.get("code"),
                        csvRecord.get("name"),
                        csvRecord.get("batch"),
                        Integer.parseInt(csvRecord.get("stock")),
                        Integer.parseInt(csvRecord.get("deal")),
                        Integer.parseInt(csvRecord.get("free")),
                        Double.parseDouble(csvRecord.get("mrp")),
                        Double.parseDouble(csvRecord.get("rate")),
                        findExpDate(csvRecord.get("exp")),
                        csvRecord.get("company"),
                        csvRecord.get("supplier")

                );
                inventoryItems.add(tutorial);
            }
            return inventoryItems;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private static Date findExpDate(String exp) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(exp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
