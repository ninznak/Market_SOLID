package ru.alex.product;

import java.util.Objects;

public class Product {
    private final int MAX_PRICE = 100_000;           // Избегание магических чисел, используем константу с понятным названием
    private final int id;
    private final String name;
    private double price;
    private ProductCategory category;
    private byte rating;

    public Product(int id, String name, double price, ProductCategory category, byte rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.rating = rating;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        this.price = price;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public byte getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id
                && Double.compare(price, product.price) == 0
                && rating == product.rating
                && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, rating);
    }
}