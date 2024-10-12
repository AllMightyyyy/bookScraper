package org.example.utility;

import com.opencsv.CSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.model.Book;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    public static List<Book> readBooksFromCsv(String filePath) {
        List<Book> books = new ArrayList<>();

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(',')
                .withQuoteChar('"')
                .build();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(csvParser)
                .build()) {

            String[] values;
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                if (values.length == 4) {
                    String title = values[0];
                    String price = values[1].replace("£", "");
                    String availability = values[2];
                    String rating = values[3];

                    books.add(new Book(title, price, availability, rating));
                } else {
                    System.err.println("Skipping malformed line: " + Arrays.toString(values));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return books;
    }
}
