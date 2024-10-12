package org.example.utility;

import org.example.model.Book;

import java.util.*;
import java.util.stream.Collectors;

public class CsvAnalyzer {

    private List<Book> books;

    public CsvAnalyzer(List<Book> books) {
        this.books = books;
    }

    public double calculateAveragePrice() {
        return books.stream()
                .mapToDouble(book -> Double.parseDouble(book.getPrice()))
                .average()
                .orElse(0);
    }

    public Map<String, Long> countBooksByRating() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getRating, Collectors.counting()));
    }

    public Map<String, Long> countBooksByAvailability() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getAvailability, Collectors.counting()));
    }

    public Book findMostExpensiveBook() {
        return books.stream()
                .max(Comparator.comparingDouble(book -> Double.parseDouble(book.getPrice())))
                .orElse(null);
    }

    public Book findLeastExpensiveBook() {
        return books.stream()
                .min(Comparator.comparingDouble(book -> Double.parseDouble(book.getPrice())))
                .orElse(null);
    }

    public List<Book> getTopRatedBooks(int n) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getRating).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public Map<String, Long> countBooksByPriceRange() {
        Map<String, Long> priceRangeCount = new HashMap<>();
        priceRangeCount.put("0 - 20", books.stream().filter(book -> Double.parseDouble(book.getPrice()) <= 20).count());
        priceRangeCount.put("21 - 50", books.stream().filter(book -> Double.parseDouble(book.getPrice()) > 20 && Double.parseDouble(book.getPrice()) <= 50).count());
        priceRangeCount.put("51 - 100", books.stream().filter(book -> Double.parseDouble(book.getPrice()) > 50 && Double.parseDouble(book.getPrice()) <= 100).count());
        priceRangeCount.put("100+", books.stream().filter(book -> Double.parseDouble(book.getPrice()) > 100).count());

        return priceRangeCount;
    }

}
