package ru.alex.basket;

import ru.alex.product.Product;

import java.util.List;

public interface Basket {
    void addToBasket(Product product);

    void removeFromBasket(Product product);

    double getBasketTotal();

    List<Product> getBasketItems();
    void clearBasket();
}

