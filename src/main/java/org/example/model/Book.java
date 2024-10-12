package org.example.model;

public class Book {
    private String title;
    private String price;
    private String availability;
    private String rating;

    public Book(String title, String price, String availability, String rating) {
        this.title = title;
        this.price = price;
        this.availability = availability;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Price: " + price + ", Availability: " + availability + ", Rating: " + rating;
    }
}
