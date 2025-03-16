package ru.alex.buyers;

import ru.alex.basket.Basket;
import ru.alex.order.Order;
import ru.alex.product.Product;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class Buyer {                   // Избегаем дублирования кода в наследниках SimpleBuyer и Последующих
    private double wallet;                          // за счет реализации основных свойств и методов в родителе
    private final String name;
    private final String surname;
    private String email;
    private Basket basket;                              // Вместо конкретной реализации используем общий интерфейс
    private final HashMap<String, List<Product>> orderHistory = new LinkedHashMap<>();


    public Buyer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void setBasket(Basket basket) {          // Принцип Dependency Inversion Principle (Инверсия зависимости)
        this.basket = basket;                       // Вместо конкретной реализации передаем интерфейс Busket
    }                                               // Так мы отделяем покупателя от конкретной реализации корзины

    public double getWallet() {
        return wallet;
    }

    public void addToWallet(double wallet) {
        this.wallet += wallet;
    }

    public void makeOrder(Order order) {
        orderHistory.put(order.getID(), order.getProducts());
    }

    public void cancelOrder(String orderId) {
        orderHistory.remove(orderHistory.remove(orderId));
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Order getOrder(String orderId) {
        return null;
    }

    public HashMap<String, List<Product>> getOrderHistory() {
        return orderHistory;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Покупатель: " + "{" + "имя ="
                + getName() + ", фамилия ="
                + getSurname() + ", email ="
                + getEmail() + '}';
    }
}
