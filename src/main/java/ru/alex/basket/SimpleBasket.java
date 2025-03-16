package ru.alex.basket;

import ru.alex.product.Product;

import java.util.ArrayList;
import java.util.List;

public class SimpleBasket implements Basket {
    private List<Product> items = new ArrayList<>();

    public void addToBasket(Product product) {
        items.add(product);
    }

    public void removeFromBasket(Product product) {
        items.remove(product);
    }

    public double getBasketTotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getBasketItems() {
        return items;
    }

    public void clearBasket() {
        items.clear();
    }
}