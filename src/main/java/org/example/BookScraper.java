package org.example;

import org.example.model.Book;
import org.example.utility.SaveBooksToCSV;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookScraper {

    public static void main(String[] args) {
        String baseUrl = "http://books.toscrape.com/catalogue/page-";
        int currentPage = 1;
        boolean hasNextPage = true;

        List<Book> books = new ArrayList<>();

        while (hasNextPage) {
            String pageUrl = baseUrl + currentPage + ".html";
            try {
                System.out.println("Scraping page: " + currentPage);
                Document doc = Jsoup.connect(pageUrl).get();
                Elements bookElements = doc.select("article.product_pod");

                for (Element bookElement : bookElements) {
                    String title = bookElement.select("h3 a").attr("title");
                    String price = bookElement.select(".price_color").text();
                    String availability = bookElement.select(".instock.availability").text().trim();
                    String ratingClass = bookElement.select(".star-rating").attr("class");
                    String rating = ratingClass.replace("star-rating", "").trim(); // Extract the rating

                    Book book = new Book(title, price, availability, rating);
                    books.add(book);
                }

                Element nextPage = doc.select("li.next a").first();
                if (nextPage == null) {
                    hasNextPage = false;
                } else {
                    currentPage++;
                }

            } catch (IOException e) {
                e.printStackTrace();
                hasNextPage = false;
            }
        }

        SaveBooksToCSV.saveToCSV(books);
    }
}
