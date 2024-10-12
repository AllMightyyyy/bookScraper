package org.example.utility;

import org.example.model.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveBooksToCSV {

    private static String escapeCSVField(String field) {
        if (field.contains(",") || field.contains("\"")) {
            field = field.replace("\"", "\"\"");
            return "\"" + field + "\"";
        }
        return field;
    }

    public static void saveToCSV(List<Book> books) {
        try (FileWriter writer = new FileWriter("books.csv")) {
            writer.append("Title,Price,Availability,Rating\n");

            for (Book book : books) {
                writer.append(escapeCSVField(book.getTitle())).append(",");
                writer.append(book.getPrice()).append(",");
                writer.append(book.getAvailability()).append(",");
                writer.append(book.getRating()).append("\n");
            }

            System.out.println("Data saved to books.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
