package org.example;

import org.example.model.Book;
import org.example.utility.CsvAnalyzer;
import org.example.utility.CsvReader;

import java.util.List;
import java.util.Map;

public class Analyst {
    public static void main(String[] args) {
        String filePath = "books.csv";
        List<Book> books = CsvReader.readBooksFromCsv(filePath);

        CsvAnalyzer analyzer = new CsvAnalyzer(books);

        double averagePrice = analyzer.calculateAveragePrice();
        System.out.println("Average Price: £" + averagePrice);

        Map<String, Long> booksByRating = analyzer.countBooksByRating();
        System.out.println("Books by Rating:");
        booksByRating.forEach((rating, count) -> System.out.println("Rating " + rating + ": " + count + " books"));

        Map<String, Long> booksByAvailability = analyzer.countBooksByAvailability();
        System.out.println("Books by Availability:");
        booksByAvailability.forEach((availability, count) -> System.out.println(availability + ": " + count + " books"));

        Book mostExpensive = analyzer.findMostExpensiveBook();
        Book leastExpensive = analyzer.findLeastExpensiveBook();
        System.out.println("Most Expensive Book: " + mostExpensive);
        System.out.println("Least Expensive Book: " + leastExpensive);

        List<Book> topRatedBooks = analyzer.getTopRatedBooks(5);
        System.out.println("Top 5 Highest Rated Books:");
        topRatedBooks.forEach(book -> System.out.println(book));

        Map<String, Long> booksByPriceRange = analyzer.countBooksByPriceRange();
        System.out.println("Books by Price Range:");
        booksByPriceRange.forEach((range, count) -> System.out.println(range + ": " + count + " books"));
    }
}
