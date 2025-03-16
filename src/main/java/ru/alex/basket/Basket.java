package ru.alex.basket;

import ru.alex.product.Product;

import java.util.List;

public interface Basket {
    public void addToBasket(Product product);

    public void removeFromBasket(Product product);

    public double getBasketTotal();

    public List<Product> getBasketItems();
    public void clearBasket();
}

